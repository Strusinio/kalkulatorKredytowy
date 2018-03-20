package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/generate")
public class PDFServlet extends HttpServlet {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			Document document = new Document();

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, buffer);
			document.open();

			PdfPTable table = new PdfPTable(5);
			addTableHeader(table);
			List<Splata> splaty = (List<Splata>) request.getSession().getAttribute("splaty");
			addRows(table, splaty);

			document.add(table);
			document.close();

			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			// setting the content type
			response.setContentType("application/pdf");
			// the contentlength
			response.setContentLength(buffer.size());
			// write ByteArrayOutputStream to the ServletOutputStream
			OutputStream os = response.getOutputStream();
			buffer.writeTo(os);
			os.flush();
			os.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	private void addTableHeader(PdfPTable table) {
		Stream.of("Numer Raty", "Kwota kapitalu", "Kwota odsetek", "Oplaty stale", "Calkowita kwota raty")
				.forEach(columnTitle -> {
					PdfPCell header = new PdfPCell();
					header.setBackgroundColor(BaseColor.LIGHT_GRAY);
					header.setBorderWidth(2);
					header.setPhrase(new Phrase(columnTitle));
					table.addCell(header);
				});
	}

	private void addRows(PdfPTable table, List<Splata> splaty) {
		for (Splata splata : splaty) {
			table.addCell(splata.getNumerRaty().toString());
			table.addCell(splata.getKwota().toString());
			table.addCell(splata.getOdsetki().toString());
			table.addCell(splata.getOplaty().toString());
			table.addCell(splata.getCalowitaKwotaRaty().toString());
		}
	}
}
