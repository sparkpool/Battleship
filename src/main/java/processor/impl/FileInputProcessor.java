package processor.impl;

import processor.intrface.IInputProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class FileInputProcessor implements IInputProcessor {
    @Override
    public List<String> process(String filePath) {
        this.validate(filePath);
        List<String> result = new ArrayList<>();
        BufferedReader br = null;
        try {
            File file = new File(filePath);
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
            br.close();
        } catch (IOException e) {
            throw new InvalidParameterException("Invalid Input file" + e.getMessage());
        }
        return result;
    }

    private void validate(String filePath) {
        if (filePath == null || filePath.length() == 0) {
            throw new InvalidParameterException("File Path is either null or empty");
        }
    }
}
