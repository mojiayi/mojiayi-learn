package list;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

import junit.framework.Assert;
import org.junit.Test;

public class TestList {
	@Test
	public void testArrayList() {
		ArrayList<String> list = new ArrayList<>();
		list.add("a");
		list.add(0, "b");
	}
	
	@Test
	public void testLinkedList() {
		LinkedList<String> list = new LinkedList<>();
		list.add("a");
		list.add(0, "b");
	}

	@Test
	public void testHashMap() {
		HashMap<String, String> map = new HashMap<>();
		map.put(null, "s");
		map.put(null, "m");
		//Assert.assertEquals("s", map.get(null));
		Assert.assertEquals("m", map.get(null));
	}

	@Test
	public void testHashtable() {
		Object obj = null;
		Hashtable<String, String> table = new Hashtable<>();
		table.put(null, "s");
		table.put("m", null);
		Assert.assertEquals("s", table.get("m"));
	}

	@Test
	public void testHeapStack() {
		Person person = new Person();
		person.setAge(10);
		person.modifyAge();
		Assert.assertEquals(9, person.getAge().intValue());
		int age = 12;
		person.modifyAge(age);
		Assert.assertEquals(12, age);

		Integer age2 = 15;
		person.modifyAge(age2);
		Assert.assertEquals(15, age2.intValue());
	}

	@Test
	public void testJavaNIO() throws IOException {
		RandomAccessFile file = new RandomAccessFile("d://test//hs_err_pid5316.log", "rw");
		FileChannel channel = file.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(48);
		while (channel.read(buffer) != -1) {
			System.out.println(new String(buffer.array()));
			buffer.flip();
		}
	}
}
