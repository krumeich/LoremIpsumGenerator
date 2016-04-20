import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class LoremIpsumGenerator {

	private static final int TESTDATA_LENGTH = 2500000;
	private static final int CHAPTER_LENGTH = 100;
	private static final int SECTION_LENGTH = 10;
	private static final String LOREMIPSUM = "Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est.";
	private static final String LF = System.getProperty("line.separator");
	private static final String HEADING_1 = "= ";
	private static final String HEADING_2 = "== ";
	private static final String HEADING_3 = "=== ";

	private StringBuilder theBuffer;

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		LoremIpsumGenerator l = new LoremIpsumGenerator();
		l.generateData();
		System.out.println("Done: " + (System.currentTimeMillis() - start) + "ms");
	}

	private void generateData() throws FileNotFoundException {
		theBuffer = new StringBuilder();
		addHeader();
		for (int i = 1; theBuffer.length() < TESTDATA_LENGTH; i++) {
			System.out.println(i);
			if (i % CHAPTER_LENGTH == 0) {
				addChapter(i / CHAPTER_LENGTH);
			}
			if (i % SECTION_LENGTH == 0) {
				addSection(i / SECTION_LENGTH);
			}
			theBuffer.append(LOREMIPSUM);
			theBuffer.append(LF);
			
		}
		writeToFile();
	}

	private void addHeader() {
		theBuffer.append(HEADING_1);
		theBuffer.append("The Lorem Ipsum Chronicles");
		theBuffer.append(LF);
		theBuffer.append(LF);
		theBuffer.append(HEADING_2);
		theBuffer.append("Preface");
		theBuffer.append(LF);
	}

	private void addChapter(int chapter) {
		theBuffer.append(LF);
		theBuffer.append(HEADING_2);
		theBuffer.append("Chapter " + chapter);
		theBuffer.append(LF);
	}

	private void addSection(int section) {
		theBuffer.append(LF);
		theBuffer.append(HEADING_3);
		theBuffer.append("Section " + section);
		theBuffer.append(LF);
	}

	private void writeToFile() throws FileNotFoundException {
		File outFile = new File("loremipsumTestData.adoc");
		PrintStream outStream = new PrintStream(outFile);
		outStream.println(theBuffer.toString());
		outStream.close();
	}

}
