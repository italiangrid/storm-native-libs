#include <iostream>
#include <stdexcept>
#include <cstdlib>
#include "posixfs.hpp"

using namespace std;

int
main(int argc, char* argv[]){

    try{

      if (argc < 2){
        
        cerr << "Usage: test-posifx <file>" << endl;
        ::exit(1);
        
      }

      
      string file = argv[1];

      fs::posixfs fs = fs::posixfs("/");

      long size = fs.get_size(file);

      cout << "size of '" << file << "': " << size << endl;



    }catch(const std::exception& ex){
      
        cerr << "Caught exception " << endl;
        
        cerr << ex.what() << endl;
        ::exit(1);
    }

}
