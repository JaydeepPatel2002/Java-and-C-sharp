import java.io.File;

public class DirSize {

	//filter = "EXE" you only count all of *.exe size
	private static long dirSize(File oFile,String filter)
	{
		long size = 0;
		File[] content = oFile.listFiles();
		for(File f : content)
		{
			if(f.isDirectory())
			{
				size += dirSize(f,filter);
			}
			else
			{
				String extension = "";
				String filename = f.getName();
				int index = filename.lastIndexOf('.');
				if (index > 0) {
				    extension = filename.substring(index + 1);
				}
				if(extension.compareToIgnoreCase(filter)==0) size += f.length();
			}
		}
		return size;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length!=2)
		{
			System.out.println("Usage: DirSize path EXT");
			System.exit(1);
		}
		String path = args[0];
		String filter = args[1];
		File oFile = new File(path);
		if(!oFile.isDirectory())
		{
			System.out.printf("%s is not dir\n",path);
			System.exit(2);
		}
		System.out.printf("%s size is %d bytes\n", path, dirSize(oFile,filter));
	}

}
