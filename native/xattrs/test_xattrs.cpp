#include <iostream>
#include "extended_attrs.hpp"
#include <stdexcept>
#include <cstdlib>

using namespace std;


void print_attrs(const string& file){

    vector<string> names = fs::get_xattr_names(file);
    
    for (int i=0; i < names.size(); i++){
        string value = fs::get_xattr_value(file, names[i]);
        cout << names[i] << " : " << value << endl;
    }
}

int
main(int argc, char* argv[]){

    try{

        if (argc < 4){
            cerr << "usage: test_xattr <file> <attr_name> <attr_value>" << endl;
            ::exit(1);
        }
        
        string file  = argv[1];
        string attr_name  = argv[2];
        string attr_value = argv[3];

        fs::set_xattr(file, attr_name, attr_value);
        fs::set_xattr(file, attr_name+"2");
        fs::set_xattr(file, attr_name+"3", "another value");

        print_attrs(file);

        if (! fs::xattr_is_set(file, attr_name)){
            throw std::runtime_error("Just set attribute not found!");
        }

        fs::remove_xattr(file,attr_name);
        if ( fs::xattr_is_set(file,attr_name) ){
            throw std::runtime_error("Just removed attribute found!");
        }

        print_attrs(file);

    }catch(const std::exception& ex){
        
        cerr << ex.what() << endl;
        ::exit(1);
    }

}
