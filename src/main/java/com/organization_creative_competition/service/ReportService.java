package com.organization_creative_competition.service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.organization_creative_competition.model.Competition;
import com.organization_creative_competition.model.Participant;
import com.organization_creative_competition.model.UserInfo;
import lombok.RequiredArgsConstructor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final CompetitionService competitionService;

    private static Font FONT_HEADER;

    private static Font FONT_NORMAL;

    static {
        try {
            BaseFont baseFont = BaseFont.createFont("asset/times-roman.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            FONT_HEADER = new Font(baseFont, 16, Font.BOLD, BaseColor.BLACK);
            FONT_NORMAL = new Font(baseFont, 10, Font.NORMAL, BaseColor.BLACK);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public ByteArrayInputStream getReportByCompetitionId(Long id) {
        Competition competition = competitionService.getById(id);

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = null;

        try {
            writer = PdfWriter.getInstance(document, out);
            document.open();
            document.setPageSize(PageSize.A4.rotate());
            document.newPage();

            Paragraph title = new Paragraph("Отчет по конкурсу '" + competition.getName() + "'", FONT_HEADER);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("Описание: " + competition.getDescription(), FONT_HEADER));

            document.add(new Paragraph("Приз: " + competition.getPrize(), FONT_HEADER));

            document.add(new Paragraph("Дата начала регистрации: " + competition.getStartDate(), FONT_HEADER));

            document.add(new Paragraph("Дата окончания регистрации: " + competition.getEndDate(), FONT_HEADER));

            document.add(new Paragraph("Всего участников: " + competition.getParticipants().size(), FONT_HEADER));

            document.add(new Paragraph("Победителей: " + (int) competition.getParticipants().stream()
                    .filter(participant -> Objects.nonNull(participant.getIsWinner()) && participant.getIsWinner()).count(), FONT_HEADER));

            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(5);
            Stream.of("Фамилия", "Имя", "Отчество", "Сообщение", "Победитель")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(headerTitle, FONT_NORMAL));
                        table.addCell(header);
                    });

            for (Participant participant : competition.getParticipants()) {
                UserInfo userInfo = participant.getUser().getUserInfo();
                table.addCell(new Phrase(userInfo.getSurname(), FONT_NORMAL));
                table.addCell(new Phrase(userInfo.getName(), FONT_NORMAL));
                table.addCell(new Phrase(userInfo.getPatronymic(), FONT_NORMAL));
                table.addCell(new Phrase(participant.getMessage(), FONT_NORMAL));
                table.addCell(new Phrase(Objects.isNull(participant.getIsWinner()) || !participant.getIsWinner() ? "Нет" : "Да", FONT_NORMAL));
            }

            document.add(table);

            JFreeChart chart = createChart(competition.getParticipants());
            BufferedImage bufferedImage = chart.createBufferedImage(500, 500);
            Image image = Image.getInstance(writer, bufferedImage, 1.0f);
            document.add(image);
            document.add(Chunk.NEWLINE);
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    private JFreeChart createChart(List<Participant> participants) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        participants.forEach(participant -> {
            String category = Objects.isNull(participant.getIsWinner()) || !participant.getIsWinner() ? "Проигравший" : "Победитель";
            int count = 0;
            try {
                count = dataset.getValue(category).intValue();
            } catch (Exception ignored) {
            }
            dataset.setValue(category, count + 1);
        });

        return ChartFactory.createPieChart("Распределение участников", dataset, true, true, false);
    }
}
