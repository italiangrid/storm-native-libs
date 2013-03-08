#include "extended_attrs.hpp"
#include <attr/xattr.h>
#include <iostream>
#include <sstream>
#include <algorithm>
#include <iterator>
#include <cerrno>

using namespace std;

void raise_error(string msg){
    char buffer[256];
    const char* errno_message = strerror_r(errno, buffer, 256);
    msg += errno_message;
    throw runtime_error(msg);
}

vector<string> parse_xattr_string(const char* xattr_names, ssize_t xattr_size){
    vector<string> strings;

    char * p = const_cast<char*>(xattr_names);
    string s;

    for (ssize_t i = 0; i < xattr_size; i++){
        if ( p[i] != '\0')
            s += p[i];
        else{
            strings.push_back(s);
            s.clear();
        }
    }
    
    return strings;

}

vector<string> 
fs::get_xattr_names(const string& path){

    // call it first to know how many attrs are set
    ssize_t attr_names_length = listxattr(path.c_str(), 0, 0);
    
    // we had an error, throw exception with msg from errno
    if (attr_names_length < 0){
        raise_error("Error retrieving extended attribute names from file '"+path+"': ");
    }

    // no attributes set, return empty vector
    if (attr_names_length == 0){
        vector<string> empty;
        return empty;
    }
    
    // attributes are set, call again and get attribute names
    char attr_name_string[attr_names_length];

    listxattr(path.c_str(),attr_name_string,attr_names_length);

    // attributes names are funnily encoded in a single, null separated string, like
    // 'user.attr1\0user.attr2\0user.attr3\0'.
    // the parse function splits the string in a vector of xattr names
    vector<string> names = parse_xattr_string(attr_name_string,
            attr_names_length);

    return names;
}


string fs::get_xattr_value(const string& path, const string& attr_name){

    ssize_t attr_value_size = getxattr(path.c_str(), 
        attr_name.c_str(),0,0);

    if (attr_value_size < 0){
        raise_error("Error retrieving extended attribute value '"+attr_name+"' from file '"+path+"': ");
    }
    
    char attr_value[attr_value_size];
    getxattr(path.c_str(),attr_name.c_str(),attr_value,attr_value_size);

    return attr_value; 
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
        raise_error("Error setting extended attribute on file '"+path+"': ");
    }
    
}

void fs::remove_xattr(const string& path,
    const string& attr_name){

    if (attr_name.empty())
        throw runtime_error("Cannot remove empty extended attribute name!");

    if ( removexattr (path.c_str(), attr_name.c_str()) ){
        raise_error(" Error removing extended attribute '"+attr_name+"' from file '"+path+"': ");
    }
}

