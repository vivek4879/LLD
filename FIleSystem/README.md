"Design an in-memory file system that supports creating files and directories, navigating paths, and basic file operations."

1. Hierarchical file system with single root directory.
2. Files store string content
3. Folders contain files and other folders.
4. Create and Delete files nad folders
5. List contents of a folder.
6. Navigate/resolve absolute paths
7. Rename and move files nad folder.
8. Retrieve full path from any file/folder reference
9. 

Core Entities:
1. File system -> Orchestrator. Owns root folder, parses paths, provides public API for all operations.External code interacts with this class.
2. File -> Represents a file. Has String content and is a leaf node.
3. Folder -> Represents directory. Contains files and other folders. Has a name. Contains child entries, privides methods to add remove and lookup children.

Class FileSystem:

-root: Folder

+FileSystem()
+createFolder(path) -> Folder
+createFile(path,content) -> File
+delete(path)
+list(path) -> List<FileSystemEntry>
//list contents of a folder
+get(path) -> FileSystemEntry
//navigate/resolve absolute path
+rename(path,newName)
+move(srcPath,desPath)



abstract class FileSystemEntry{
    -name:String
    -parent: Folder?

    +FileSystemEntry(name)
    +getName() -> String
    +getParent() -> Folder?
    +setName(name)
    +setParent(Folder?)
    +getpath() -> path
}


Class File extends FileSystemEntry:
    -content: String
    -isDirectory = false

    +File(name, content)
    +getContent() -> String
    +setContent(content)
    +isDirectory() -> False

Class Folder extends FileSystemEntry:
    -Children: Map<String,FileSystemEntry >

    +Folder(name)
    +isDirectory() -> False
    +addChild(entry)
    +removeChild(name) -> FileSystemEntry
    +getChild(name) ->FileSystemEntry
    +hasChild(name) ->Boolean
    +getChildren() -> List<FileSystemEntry>



Define core logic
Handle edge cases
Assuming single threaded behavior for now.





