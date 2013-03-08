#ifndef EXTENDED_ATTRS_H
#define EXTENDED_ATTRS_H

#include <string>
#include <vector>

namespace fs {

  // Returns the extended attribute names defined for the given path
  std::vector<std::string> get_xattr_names (const std::string& path);

  // Returns the extended attribute values 
  std::string get_xattr_value (const std::string& path, const std::string& attr_name);

  // Returns true if attr_name is set on path, false otherwise
  bool xattr_is_set(const std::string& path, const std::string& attr_name);

  // Sets an extended attribute
  void set_xattr(const std::string& path, 
    const std::string& name);

  // Sets an extended attribute with a given value
  void set_xattr(const std::string& path, 
    const std::string& name, 
    const std::string& value);

  // Removes an extended attribute from a file
  void remove_xattr(const std::string& path,
    const std::string& name);

}
#endif /* #ifndef EXTENDED_ATTRS_H */
