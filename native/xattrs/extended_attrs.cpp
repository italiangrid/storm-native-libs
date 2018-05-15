#include "extended_attrs.hpp"
// added include sys/types to solve the following error
// on EL7 
// /usr/include/attr/xattr.h:43:8: error: 'ssize_t' does not name a type
# #include <sys/types.h>
#include <attr/xattr.h>
#include <iostream>
#include <sstream>
#include <algorithm>
#include <iterator>
#include <cerrno>
#include <stdexcept>
#include <cstring>
#include <cstdio>

using namespace std;

static int const ERRNO_BUF_SIZE = 256;
static int const ATTR_NAMES_BUF_SIZE = 1024;
static int const ATTR_VALUE_BUF_SIZE = 1024;
static int const SYSCALL_RETRY_COUNT = 2;
static int const ERROR_MSG_BUF_SIZE = 1024;

static void raise_error(const char* msg, const char* file, const char* attr_name){
    char buffer[ERRNO_BUF_SIZE];
    const char* errno_message = strerror_r(errno, buffer, ERRNO_BUF_SIZE);

    char* cerr_msg = new char[ERROR_MSG_BUF_SIZE];

    memset(cerr_msg,0,ERROR_MSG_BUF_SIZE);

    sprintf(cerr_msg,"(File: %s) %s %s: %s", 
        file,
        msg, 
        (attr_name == 0 ? "": attr_name), 
        errno_message);

    string error_message(cerr_msg);

    delete[] cerr_msg;

    throw runtime_error(error_message);
}

static vector<string> parse_xattr_string(char const* buf_begin, char const* buf_end){

    vector<string> names;

    char const* p1 = buf_begin;

    while (p1 != buf_end){
        char const* p2 = p1;
        while (*p2++);
        names.push_back(string(p1,p2));
        p1 = p2;
    }
    
    return names;
}

static const char* retry_list_attr(const string& file, ssize_t* attr_names_length){

    int bufsize = ATTR_NAMES_BUF_SIZE;
    int num_calls = 0;

    char* attr_names_buf = 0;
    ssize_t attr_names_strlen;
    
    do{
        num_calls++;
        char tmp_buf[bufsize];
        attr_names_strlen = listxattr(file.c_str(),tmp_buf, bufsize);

        if (attr_names_strlen < 0){
            
            if (errno == ERANGE){
                bufsize *= 2;
                continue;
            }

            raise_error("Error retrieving extended attribute names",
                file.c_str(),
                0);
        }
        
        attr_names_buf = new char[attr_names_strlen];
        memcpy(attr_names_buf,tmp_buf,attr_names_strlen);
        break;

    } while (num_calls < SYSCALL_RETRY_COUNT);

    *attr_names_length = attr_names_strlen;

    if (attr_names_strlen >= 0) 
        return attr_names_buf;

    // we had an error 
    raise_error("Error retrieving extended attribute names", file.c_str(),0);
}

static const char* retry_get_attr_value(const string& file, const string& attr_name, ssize_t* attr_length){

    int bufsize = ATTR_VALUE_BUF_SIZE;
    int num_calls = 0;

    char* attr_value_buf = 0;
    ssize_t attr_value_length;

    do{
        num_calls++;
        char tmp_buf[bufsize];
        memset(tmp_buf,0, bufsize);
        attr_value_length = getxattr(file.c_str(), attr_name.c_str(),
            tmp_buf, bufsize);

        if (attr_value_length < 0){

            if (errno == ERANGE){
                bufsize *=2;
                continue;
            }

            raise_error("Error retrieving extended attribute", file.c_str(), attr_name.c_str());
        }

        attr_value_buf = new char[attr_value_length];
        memcpy(attr_value_buf,tmp_buf,attr_value_length);
        break;
    } while (num_calls < SYSCALL_RETRY_COUNT);

    *attr_length = attr_value_length;
    if (attr_value_length >= 0)
        return attr_value_buf;

    raise_error("Error retrieving extended attribute", file.c_str(), attr_name.c_str());
}

bool
fs::xattr_is_set(const string& file, const string& attr_name){

    ssize_t attr_value_length = getxattr(file.c_str(), attr_name.c_str(), 0, 0);

    if (attr_value_length < 0){

        if (errno == ENOATTR)
            return false;

        raise_error("Error retrieving extended attribute", file.c_str(), attr_name.c_str());
    }

    return true;
}

vector<string> 
fs::get_xattr_names(const string& path){

    ssize_t attr_names_strlen = 0;

    const char* attr_names_buf = retry_list_attr(path, &attr_names_strlen);

    // attributes names are funnily encoded in a single, null separated string, like
    // 'user.attr1\0user.attr2\0user.attr3\0'.
    // the parse function splits the string in a vector of xattr names
    vector<string> names = parse_xattr_string(attr_names_buf, 
            attr_names_buf + attr_names_strlen);

    delete[] attr_names_buf;
    return names;
}


string fs::get_xattr_value(const string& path, const string& attr_name){

    ssize_t attr_value_length = 0;

    const char *attr_value = retry_get_attr_value(path, attr_name, &attr_value_length);

    string s(attr_value, attr_value+attr_value_length);
    delete[] attr_value;

    return s; 
}

void fs::set_xattr(const string& path,
    const string& attr_name){

    set_xattr(path,attr_name,"");
}

void fs::set_xattr(const string& path,
    const string& attr_name,
    const string& attr_value){

    if (attr_name.empty())
        throw runtime_error("Cannot set empty extended attribute name!");

    if ( setxattr (path.c_str(), attr_name.c_str(),
            attr_value.c_str(), attr_value.size(), 0) ){
        raise_error("Error setting extended attribute", path.c_str(), attr_name.c_str());
    }
    
}

void fs::remove_xattr(const string& path,
    const string& attr_name){

    if (attr_name.empty())
        throw runtime_error("Cannot remove empty extended attribute name!");

    if ( removexattr (path.c_str(), attr_name.c_str()) ){
        raise_error("Error removing extended attribute", path.c_str(), attr_name.c_str());
    }
}

