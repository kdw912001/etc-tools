package test.byteio;
import java.io.*;
public class TestDataIO {
	public void fileSave() {
		//DataOutputStream Ŭ������ ���� �������� ����� �� �ִ�
		//�޼ҵ带 �����ϴ� ������Ʈ�� Ŭ������.
		/*try (FileOutputStream fout = new FileOutputStream("member.dat");
				DataOutputStream dout = new DataOutputStream(fout);){
			
		}���۷����� ���� �� ���� ���ٸ� �ϳ��� ���۷����� ������ ��.*/
		//�ѹ��� �����ϸ� ���� ���� ó�� 2���� ���۷����� ������ �ʿ䰡 ����.
		try(DataOutputStream dout = new DataOutputStream(
				new FileOutputStream("member.dat"))) {
			//dout.writeUTF("ȫ�浿"); //Data ��Ʈ���� ����� ������� ���� �о�� ��.
			String name = "ȫ�浿";
			int age = 27;
			char gender = '��';
			double height = 178.5;
			dout.writeUTF(name);//���� �� �� UTF�� �о�� ��
			dout.writeInt(age);
			dout.writeChar(gender);
			dout.writeDouble(height);
			dout.flush();
			
			System.out.println("���Ͽ� ��� �Ϸ�.");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fileRead() {
		//���۷����� ���� �� ���� ���ٸ� �ϳ��� ���۷����� ������ ��.
		/*try(FileInputStream fin = new FileInputStream("member.dat");
				DataInputStream din = new DataInputStream(fin);)*/
		try (DataInputStream din = new DataInputStream(
				new FileInputStream("member.dat"));){
			//�� ����� ������� �о�;� ��.
			String name = din.readUTF();
			int age = din.readInt();
			char gender = din.readChar();
			double height = din.readDouble();
			
			System.out.println(name + ", "+age +", "+gender+", "+height);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[]args) {
		//data ����� ���� ��Ʈ�� �׽�Ʈ
		TestDataIO test = new TestDataIO();
		//test.fileSave();
		//member.dat�� ����� ���� byte���� ������ ������ ���� �̻��ϰ� ����
		test.fileRead();
	}
}

package test.byteio;
import java.io.*;
public class TestDataIO2 {
	public void fileSave() {
		//try ���� ����� �����ؼ� close �������� ���� ���� �� �ĺ���
		//exception ������ �� �Ẹ��
		FileOutputStream fout = null;
		DataOutputStream dout = null;
		try {
			fout=new FileOutputStream("member.dat");
			dout=new DataOutputStream(fout);
			//dout.writeUTF("ȫ�浿"); //Data ��Ʈ���� ����� ������� ���� �о�� ��.
			String name = "ȫ�浿";
			int age = 27;
			char gender = '��';
			double height = 178.5;
			dout.writeUTF(name);//���� �� �� UTF�� �о�� ��
			dout.writeInt(age);
			dout.writeChar(gender);
			dout.writeDouble(height);
			dout.flush();
			
			System.out.println("���Ͽ� ��� �Ϸ�.");
		}catch(FileNotFoundException e) {
			System.out.println("������ �����ϴ�.");
		}catch(IOException e) {	e.printStackTrace();
		}catch (Exception e) {	e.printStackTrace();
		}finally {
			try {
				dout.close();
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void fileRead() {
		FileInputStream fin = null;
		DataInputStream din = null;
		try {
			fin = new FileInputStream("test.dat");
			din = new DataInputStream(fin);
			//�� ����� ������� �о�;� ��.
			String name = din.readUTF();
			int age = din.readInt();
			char gender = din.readChar();
			double height = din.readDouble();
			
			System.out.println(name + ", "+age +", "+gender+", "+height);
		}catch(FileNotFoundException e) {
			System.out.println("������ �������� �ʽ��ϴ�.");
		}catch(IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				din.close();
				fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[]args) {
		TestDataIO test = new TestDataIO();
		//test.fileSave();
		test.fileRead();
	}

}

package test.byteio;
import java.io.*;
public class TestObjectIO {
	public void fileSave() {
		//���� ��Ͽ� ����� ��ü�迭 �غ�
		Student[] sar = {
				new Student(12, "ȫ�浿",4.43,"�濵�а�"),
				new Student(25, "�̼���",4.5,"ü���а�"),
				new Student(37, "�念��", 3.87,"��������а�")
		};
		
		try(ObjectOutputStream objOut = new ObjectOutputStream(
				new FileOutputStream("student.dat"));){
			for(int i=0; i<sar.length;i++) {
				objOut.writeObject(sar[i]);
			}
			System.out.println("���� ��� �Ϸ�.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fileRead() {
		int count = 0;
		//�迭������ ���ڸ��� �ȵǱ� ������ �˳��� ����
		Student[] sar = new Student[10];
		try(ObjectInputStream objIn = new ObjectInputStream(
				new FileInputStream("student.dat"))){
			while(true) {
				/*Student std = (Student)objIn.readObject();
				//Object�� ���� Ŭ�����̱� ������ �ٿ�ĳ����*/
				//���Ͱ��� �� �ʿ� ���� �Ʒ�ó�� �� �������� ����
				sar[count] = (Student)objIn.readObject();
				count++;
			}
		}catch(EOFException e) {
			System.out.println("���� �б� �Ϸ�");
			for(int i=0; i<count;i++) {
				System.out.println(sar[i]); //toString�������̵�
			}
		}catch(Exception e) {
			//System.out.println("���� �б� �Ϸ�.");
			//->EOFException�߻� ��� catch�� �ۼ�
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// ��ü����� �׽�Ʈ
		TestObjectIO test = new TestObjectIO();
		test.fileSave();
		test.fileRead();
	}
}

package io.silsub2;
import java.util.*;
import java.io.*;
public class SungjukProcess {
	private Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		SungjukProcess test = new SungjukProcess();
		//test.sungjukSave();
		test.scoreRead();
	}
	public void sungjukSave() {
		
		try(DataOutputStream dout = new DataOutputStream(
				new FileOutputStream("score.dat"));) {
			do{
				System.out.print("���� ���� �Է� : ");
				int kor = sc.nextInt();
				dout.writeInt(kor);
				System.out.print("���� ���� �Է� : ");
				int eng = sc.nextInt();
				dout.writeInt(eng);
				System.out.print("���� ���� �Է� : ");
				int ma = sc.nextInt();
				dout.writeInt(ma);
				
				int sum = kor+eng+ma;
				dout.writeInt(sum);
				double avg = (kor+eng+ma)/3;
				dout.writeDouble(avg);
				System.out.print("��� �����Ͻðڽ��ϱ�?(y/n) : ");
			}while(sc.next().toUpperCase().charAt(0) == 'Y');
			System.out.println("score.dat�� ���� �Ϸ�.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void scoreRead() {
		/*FileInputStream�� readDouble() the next eight bytes of this input stream, interpreted as a double.
		 * EOFException - if this input stream reaches the end before reading eight bytes.
		 * ���������� �Է¹��� double���� EOFException(���� ������)�� �߻��ϸ� �ݺ��� ����
		 */
		int count = 0;
		int totalSum=0;
		double totalAvg=0;
		try(DataInputStream din = new DataInputStream(
				new FileInputStream("score.dat"));){
			
			do{
				int kor = din.readInt();
				int eng = din.readInt();
				int ma = din.readInt();
				int sum = din.readInt();
				double avg = din.readDouble();
						
				//System.out.println(kor+" "+eng+" "+ma+" "+sum+" "+avg);
				System.out.printf("%5d%5d%5d%5d%7.2f\n", kor,eng,ma,sum,avg);
				//printf �Ҽ��� ���� �ڵ����� �ݿø� ��.
				totalSum +=sum;
				totalAvg +=avg;
				count++;
			}while(true);
			//System.out.println("��ü���� : "+totalSum+"��ü ��� : "+totalAvg+"���� Ƚ�� : "+count);
			
		}catch(EOFException e) {//���������� ������ �� readDouble()�� ���� �� ����Ǵ� ���� 
			System.out.printf("Ƚ�� : %2d ����� : %2d ����� �� : %2.2f\n", count,totalSum/count,totalAvg/count);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}

package io.silsub3;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
public class Book implements Serializable{
	private static final long serialVersionUID = 2126172264279034891L;
	private String title;
	private String author;
	private int price;
	private Calendar dates;
	private double discountRate;
	
	public Book() {}
	public Book(String title, String author, int price, Calendar dates, double discountRate) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.dates = dates;
		this.discountRate = discountRate;
	}

	public String getTitle() {	return title;}
	public void setTitle(String title) {this.title = title;	}
	public String getAuthor() {	return author;}
	public void setAuthor(String author) {	this.author = author;}
	public int getPrice() {return price;}
	public void setPrice(int price) {	this.price = price;}
	public Calendar getDates() {return dates;}
	public void setDates(Calendar dates) {this.dates = dates;	}
	public double getDiscountRate() {return discountRate;}
	public void setDiscountRate(double discountRate) {this.discountRate = discountRate;}
	public static long getSerialversionuid() {	return serialVersionUID;}
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy'��'MM'��'dd'��'�Ⱓ");
		
		return title+" "+author+" "+price+" "+sdf.format(dates.getTime())+" "+discountRate;
	}
}

package io.silsub3;
import java.util.*;
import java.io.*;
public class BookManager {
	private Scanner sc = new Scanner(System.in);
	
	public BookManager() {}
	
	public void fileSave() {
		Book[] b = { //new GregorianCalendar();
				new Book("�ڹ�1", "��1", 1000, new GregorianCalendar(1990,11,11), 1.1),
				new Book("�ڹ�2","��2",1000, new GregorianCalendar(1990,11,12),1.1),
				new Book("�ڹ�3","��3",1000, new GregorianCalendar(1990,11,12),1.1),
				new Book("�ڹ�4","��4",1000, new GregorianCalendar(1990,11,12),1.1),
				new Book("�ڹ�5","��5",1000, new GregorianCalendar(1990,11,12),1.1),
		};
		try(ObjectOutputStream objOut = new ObjectOutputStream(
				new FileOutputStream("books.dat", true));){
			//��� ������ ������ �ڵ����� ������ ����
			//��� ������ ������, ���� ���� ������ ����� 
			//���ξ��� ���·� ������ ����.
			//������½�Ʈ�� �����ÿ� �߰�����(append) ��带 true�� �ϸ�
			//��������� ���� �� ���� ������ �״�� �ΰ� ���� �ڿ� �߰����Ⱑ ��.
			//�����Ǹ� �⺻�� false// true�� ���� ���� ������ ���ΰ� ��� ��
			//��ü ��� ��Ʈ�������� ���� ��
			for(int i=0; i<b.length;i++) {
				objOut.writeObject(b[i]);
			}
			System.out.println("books.dat�� ����Ϸ�!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void fileRead() {
		Book[] bk = new Book[10];
		int count=0;
		try (ObjectInputStream objIn = new ObjectInputStream(
				new FileInputStream("books.dat"))){
			while(true) {
				bk[count]=(Book)objIn.readObject();
				count++;
			}
			
		}catch(EOFException e) {
			for(int i=0; i<count;i++) {
				System.out.println(bk[i]);
			}
			System.out.println("books.dat �б� �Ϸ�!");
		}catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
package io.silsub3;

import replay.BookManager;
public class TestBookManager {
	public static void main(String[]args) {
		BookManager bm = new BookManager();
		//bm.fileSave();
		bm.fileRead();
	}
}

package test.chario;
import java.util.*;
import java.io.*;
public class TestCFileIO1 {
	private Scanner sc = new Scanner(System.in);
	
	public TestCFileIO1() {}
	
	//���ڽ�Ʈ������ ���Ͽ� ���� ó��
	public void fileSave() {
		FileWriter fw = null;
		
		System.out.print("������ ���ϸ� : ");
		String fileName = sc.next();
		
		try {
			fw = new FileWriter(fileName,true);//������ϰ� ��Ʈ��(���) ����
			//��� ������ ������ ������ ���� ����
			//��� ������ ������, ���ξ��� ���·� ����
			
			fw.write('A');
			fw.write("java program \n");
			char[] charr = {'a','p','p','l','e'};
			fw.write(charr);
			fw.flush(); //��Ʈ������ ������ �о������ �۾�
						//���� ��Ʈ���� ���� �����Ͱ� �ִµ� �װ� �� �о����
			
			System.out.println("���� ��� ���� �Ϸ�");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fw.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//���ڽ�Ʈ������ ���Ϸκ��� �о�ͼ� �ֿܼ� ���
	public void fileRead() {
		//FileReader fr = null;
		
		System.out.print("���� ���ϸ� : ");
		String fileName = sc.next();
		
		try(FileReader fr = new FileReader(fileName);) {
			//����� ������ ���� ���� ��. ���� ���� ���� �ֱ⶧����;�� ����
			//try ���� �̷������� �ۼ��ϰ� �Ǹ� finally �� close()���� ����
			
			//fr = new FileReader(fileName);
			//������ ������ ������
			
			int readData;
			while((readData = fr.read()) != -1) { //read()�� ���� �ϳ��� ����
				System.out.print((char)readData); //int���̱� ������ ����ȯ
			}
			System.out.println("\n�����б� �Ϸ�");
		} catch (Exception e) { 
			//���� ���� ó���� ������ ������ ���� Exception
			//�ϳ��� �����޽����� ����ҰŸ� Exception �ϳ��� �ۼ�
			e.printStackTrace();
		}/*finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
	}
	
	public static void main(String[] args) {
		// ���ڱ�� ��������� �׽�Ʈ
		TestCFileIO1 test = new TestCFileIO1();
		test.fileSave();
		test.fileRead();
	}
}

