package processor.impl;

import processor.intrface.IOutputProcessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;

public class FileOutputProcessor implements IOutputProcessor {

    @Override
    public void process(String filePath, List<String> output) {
        this.validate(filePath);
        File file = new File(filePath);
        deleteFile(file);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (String line : output) {
                bw.write(line);
                bw.write("\n");
            }
        } catch (IOException e) {
            throw new InvalidParameterException("Invalid Output file " + e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                throw new InvalidParameterException("Invalid Output file " + e.getMessage());
            }
        }
    }

    private void deleteFile(File file) {
        if (file.exists()) {
            boolean isDeleted = file.delete();
            if (!isDeleted) {
                throw new InvalidParameterException("Output file already exist and there is a problem deleting that file");
            }
        }
    }

    private void validate(String filePath) {
        if (filePath == null || filePath.length() == 0) {
            throw new InvalidParameterException("File Path is either null or empty");
        }
    }
}
