import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class TestStudentIO {
	private static final String[] NAMES = {
			"Adams",
			"Baker",
			"Clark",
			"Davis",
			"Evans",
			"Frank",
			"Ghosh",
			"Hills",
			"Irwin",
			"Jones",
			"Klein",
			"Lopez",
			"Mason",
			"Nalty",
			"Ochoa",
			"Patel",
			"Quinn",
			"Reily",
			"Smith",
			"Trott",
			"Usman",
			"Valdo",
			"White",
			"Xiang",
			"Yakub",
			"Zafar"	
	};
	
	private static final int MAX_LEN_NAME = 50;
	private static final int MAX_LEN_ADDR = 500;
	
	private static void GenData(ArrayList<Student> data)
	{
		for(int i=0;i<10;i++)
		{
			Student oStudent = new Student(NAMES[i],"1000 Rae St",i+20,true,(float)Math.random()*100);
			data.add(oStudent);
		}
	}
	
	private static byte[] floattoByteArray(float value)
	{
		int intBits = Float.floatToIntBits(value);
		return inttoByteArray(intBits);
	}
	
	private static float ByteArraytoFloat(byte[] ba)
	{
		int nVal = ByteArrayToInt(ba);
		return Float.intBitsToFloat(nVal);
	}
	
	private static byte[] booleantoByteArray(boolean value)
	{
		return new byte[] {(byte) (value?1:0)};
	}
	
	private static boolean ByteArraytoBoolean(byte[] ba)
	{
		return ba[0]==1;
	}
	
	private static byte[] inttoByteArray(int value)
	{
		int intBits = value;
		return new byte[] {(byte)(intBits>>24),
				(byte)(intBits>>16),
				(byte)(intBits>>8),
				(byte)(intBits)};
	}
	
	private static int ByteArrayToInt(byte[] ba)
	{
		return ba[0]<<24 | (ba[1] & 0xFF)<<16 | (ba[2] & 0xFF)<<8 | ba[3] & 0xFF;
	}
	
	private static byte[] stringtoFixLenByteArray(String s,int len)
	{
		byte[] rs = new byte[len];
		byte[] src = s.getBytes();
		System.arraycopy(src,0, rs, 0, src.length<len?src.length:len);
		return rs;
	}
	
	private static void Write(ArrayList<Student> data,String fn)
	{
		try (OutputStream out = new FileOutputStream(fn);)
		{
			for(Student oStudent:data)
			{
				out.write(stringtoFixLenByteArray(oStudent.getName(),MAX_LEN_NAME));
				out.write(stringtoFixLenByteArray(oStudent.getAddr(),MAX_LEN_ADDR));
				out.write(inttoByteArray(oStudent.getAge()));
				out.write(booleantoByteArray(oStudent.inClass()));
				out.write(floattoByteArray(oStudent.getGPA()));
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	private static byte[] readFile(InputStream io, int len) throws IOException,ReadFailedException
	{
		byte[] ba = new byte[len];
		int nRead = io.read(ba);
		if(nRead!=len)
		{
			throw new ReadFailedException(len,nRead);
		}
		return ba;
	}
	private static void Read(ArrayList<Student> data,String fn)  throws IOException
	{
		try (InputStream io = new FileInputStream(fn);)
		{
			while(io.available()>0)
			{
				
				String name = new String(readFile(io,MAX_LEN_NAME),StandardCharsets.UTF_8);
				String addr = new String(readFile(io,MAX_LEN_ADDR),StandardCharsets.UTF_8);
				int age = ByteArrayToInt(readFile(io,4));
				boolean inclass = ByteArraytoBoolean(readFile(io,1));
				float gpa = ByteArraytoFloat(readFile(io,4));
				data.add(new Student(name,addr,age,inclass,gpa));
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ReadFailedException e)
		{
			System.out.print(e.getMessage());
		}
	}
	
	private static void Readex(ArrayList<Student> data,String fn)  throws IOException
	{
		try (DataInputStream io = new DataInputStream(new FileInputStream(fn)))
		{
			while(io.available()>0)
			{
				data.add(new Student(io.readUTF(),io.readUTF(),
						io.readInt(),io.readBoolean(),io.readFloat()));
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	private static void Writeex(ArrayList<Student> data,String fn)
	{
		try (DataOutputStream io = new DataOutputStream(new FileOutputStream(fn)))
		{
			for(Student oStudent:data)
			{
				io.writeUTF(oStudent.getName());
				io.writeUTF(oStudent.getAddr());
				io.writeInt(oStudent.getAge());
				io.writeBoolean(oStudent.inClass());
				io.writeFloat(oStudent.getGPA());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void Readexx(ArrayList<Student> data,String fn)  throws IOException
	{
		try (ObjectInputStream io = new ObjectInputStream(new FileInputStream(fn));)
		{
			data = (ArrayList<Student>)io.readObject();
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	
	}
	
	private static void Writeexx(ArrayList<Student> data,String fn)
	{
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fn));)
		{
			out.writeObject(data);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Student> arStudents = new ArrayList<>();
		GenData(arStudents);
		for(Student s:arStudents)System.out.print(s);
		try {
		Writeexx(arStudents,"Student.dat");
		while(arStudents.size()>0)arStudents.remove(0);
		Readexx(arStudents,"Student.dat");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		for(Student s:arStudents)System.out.print(s);
		
	}

}
