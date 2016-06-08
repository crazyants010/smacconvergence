import java.io.File;
import java.io.RandomAccessFile;

public class FileGenerator {

	public String generateFile(String data, int reqid, int devid) {
		File f = new File(devid + "_" + reqid + ".xml");
		try {
			RandomAccessFile rf = new RandomAccessFile(f, "rw");
			rf.writeBytes(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return devid + "_" + reqid + ".xml";
	}

	public void deleteFile(String s) {
		File f = new File(s);
		f.delete();
	}
}
