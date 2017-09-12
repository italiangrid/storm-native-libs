/*
 *
 *  Copyright (c) Istituto Nazionale di Fisica Nucleare (INFN). 2006-2010.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @file   InvalidPermissionOnFileException.java
 * @author Riccardo Murri &lt;riccardo.murri@ictp.it&gt;
 *
 * Source code for class InvalidPermissionOnFileException
 *
 */
package it.grid.storm.filesystem;

/**
 * Thrown when an operation is attempted on the filesystem, for which the StoRM
 * process has insufficient privileges (see ENOPERM system error code).
 * Corresponds in usage fs::permission_denied exception thrown by the C++
 * filesystem code, but the name has been retained from old
 * it.grid.storm.wrapper code.
 *
 *
 * @author Riccardo Murri &lt;riccardo.murri@ictp.it&gt;
 * @version $Revision: 1.1 $
 */
public class InvalidPermissionOnFileException extends FilesystemError {

  public InvalidPermissionOnFileException(final String msg) {

    super(msg);
  }
}
