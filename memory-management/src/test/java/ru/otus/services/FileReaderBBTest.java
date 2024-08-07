package ru.otus.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.services.filereader.FileReader;
import ru.otus.services.filereader.FileReaderBB;

public class FileReaderBBTest {

    @DisplayName("Чтение содержимого файла")
    @Test
    void readFileTest() {
        //given
        String path = "src/test/resources";
        String file = "read_test.txt";
        FileReader reader = new FileReaderBB(path);

        //when
        String content = reader.read(file);

        //then
        assert content.equals("READING WAS SUCCESSFUL");
    }
}
