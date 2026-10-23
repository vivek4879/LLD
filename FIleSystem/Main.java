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

class FileSystem{
    private static FileSystemEntry root;


    FileSystem(){
        this.root = new FileSystemEntry("/");
    }

    public FileSystemEntry createFolder(String path){

    }
    public FileSystemEntry createFile(String path, String content){
        //parse the path and extract parent folder and file name
        if(path.equals("/")){
            throw InvalidPathException
        }

        FileSystemEntry parent = resolve(path);
        String fileName = extractName(path);
        if(resolvedPath.hasChild(fileName)){
            throw AlreadyExistsException
        }
        FileSystemEntry newFile = new FileSystemEntry(fileName, content)

        parent.addChild(newFile);
        return file;
    }

    public FileSystemEntry createFolder(String path , String name){
        if(path.equals("/")){
            throw InvalidPathException
        }

        FileSystemEntry parent = resolvePath(path);
        String folderName = extractName(path);
        if(folderName.hasChild(name)){
            throw AlreadyExistsException
        }
        FileSystemEntry folder = new Folder(folderName)
        parent.addChild(folder);
        return folder;

    }

    public FileSystemEntry get(String path){
        return resolve(path);
    }

    public List<FileSystemEntry>  list(String path){
        FileSystemEntry entry = resolve(path);

        if(!entry.isDirectory())throw NotaDIrectoryException;
        return entry.getChilderen();
    }

    public void delete(String path){
        if(path == "/")throw
        FileSystemEntry parent = resolve(path);
        String name = extractName(path);
        removed = parent.removeChild(name);
        if(removed == null)throw

    }

    public void rename(String newName, path){
        if(path == "/"throw)
        if(!isValid(newName))throw

        parent = resolve(path)
        curName = extractName(path)
        is(!parent.hasChild(curName))throw
        is(parent.hasChild(newName))throw
        
        child = parent.removeChild(curName);
        child.setName(newName);
        parent.addChild(child);
    }

    public void move(String oldPath, String newPath){
        if(newPath == "/")throw;
        //get src entry and its parent
        oldParent = resolve(oldPath);
        oldName = extractName(oldPath);
        if(oldParent == null)throw;
        oldEntry = oldParent.getChild(name);
        if(oldEntry == null)throw;
        
        //get des entry and its parent
        newParent = resolve(newPath);
        newName = extractName(path)
        if(newParent == null)throw;
        if(newParent.hasChild(newName))throw

        //cannot move folder into itsef or decendensts
        if(oldEntry.isDirectory()){
            current = newParent;
            while(current !=null ){
                if(current == oldEntry)throw;
                current = current.getParent();
            }
            

        }
        
        oldParent.removeChild(oldName);
        oldEntry.setName(newName);
        newParent.addChild(oldEntry);

    }

    public FileSystemEntry resolvePath(String path){
        if(path == null or path == "")throw
        if(!path.startsWith("/"))throw
        if(path == "/")return root;
        String[] parts = path.substring(1).split("/");

        current = root;
        for(part:parts){
            if(part == null)throw;
            if(!current.isDirectory())throw;
            current = current.getChild(part);
            if(current == null)throw;
        }
        return current;
    }

    public FileSystemEntry resolveParent(String path){
        if(path == null or path == "")throw
        if(!path.startsWith("/"))throw
        if(path == "/")throw;
        int lastSlashIndex = path.lastIndexOf("/");
        parentPath = lastSlashIndex == 0?"/":path.substring(0,lastSlashIndex);
        entry = resolvePath(parentPath)

        if(!entry.isDirectory())throw;
        return entry;
    }

    public String extractName(String path){
        if(path == null or path == "")throw
        if(!path.startsWith("/"))throw
        int lastSlashIndex = path.lastIndexOf("/");
        String name = substring(lastSlashIndex+1);

        if(name == null)throw;
        return name;
    }
}
/home/home1/file.txt
abstract class FileSystemEntry{
    public String getPath(FileSystemEntry node){
        Deque<String> stack = new ArrayDeque<>();
        FileSystemEntry cur = node;
        while(cur != null && cur.parent != null){
            stack.push(cur.getName());
            cur = cur.getParent();
        }
        StringBuilder sb = new StringBuilder("/");
        while(!stack.isEmpty()){
            sb.append(stack.pop());
            if(!stack.isEmpty()){
                sb.append("/");
            }
        }
        return sb.toString();
    }
    public abstract boolean isDirectory();
}

Class File extends FileSystemEntry:
    -content: String
    -isDirectory = false

    +File(name, content)
    +getContent() -> String
    +setContent(content)
    +isDirectory() -> False

class File extends FileSystemEntry{
    private String content;
    prvate Boolean isDirectory;

    File(String name,String content){
        super(name);
        this.content = content;
    }
    
    public boolean isDirectory(){
        return false;
    }
}

Class Folder extends FileSystemEntry:
    -Children: Map<String,FileSystemEntry >

    +Folder(name)
    +isDirectory() -> False
    +addChild(entry)
    +removeChild(name) -> FileSystemEntry
    +getChild(name) ->FileSystemEntry
    +hasChild(name) ->Boolean
    +getChildren() -> List<FileSystemEntry>

class Folder extends FileSystemEntry{
    Map<String,FileSystemEntry> children;

    Folder(String name){
        super(name);
        this.children = 
    }

    public boolean isDirectory(){
        return true;
    }

    public boolean addChild(FileSystemEntry entry){
        if(entry == null)return false;
        if(children.containsKey(entry.getName()))return false;
        children.put(entry.getName(), entry);
        entry.setParent(this);
        return true;
    }

}

