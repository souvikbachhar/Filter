package com.souvik;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class PDFReader {

	public static void main(String[] args) throws DocumentException {
		PdfReader reader;
		Document document = new Document();
		try {

			reader = new PdfReader("D:\\AWSPracticeQuestions.pdf");
			PdfWriter.getInstance(document, new FileOutputStream(new File("D:\\AWSPracticeQuestionsTest.pdf")));
			document.open();
			int n = reader.getNumberOfPages();
			int count = 0;
			for (int i = 1; i <= n; i++) {
				String textFromPage = PdfTextExtractor.getTextFromPage(reader, i);
				// System.out.println(textFromPage);
				String textByLine[] = textFromPage.split("\n");
				for (int j = 0; j < textByLine.length; j++) {
					if (textByLine[j].contains("QUESTION")) {
						++count;
					}
					createNewPdf(document, textByLine[j], count);

				}
			}
			document.close();
			System.out.println(count);
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void createNewPdf(Document document, String textByLine, int count) {
		try {
			// p.setAlignment(Element.ALIGN_CENTER);
			if (textByLine.contains("QUESTION")) {
				Font f = new Font();
				f.setStyle(Font.BOLD);
				f.setSize(10);
				document.add(new Paragraph("QUESTION : " + count, f));
			} else {
				Font f = new Font();
				f.setStyle(Font.NORMAL);
				f.setSize(8);
				document.add(new Paragraph(textByLine, f));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
