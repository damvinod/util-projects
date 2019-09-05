package com.damvinod.compress.sevenz;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ZipUnzip {

    private ZipUnzip() {

    }
    
    public static void main(String args[]){
    	
    	  File directory = new File("D:\\test\\NRIC_Components\\1");

          // compress recursive directory
          try {
          	
          	File[] files = directory.listFiles();
          	
          	compress("D:\\test\\NRIC_Components\\1\\Tess4j.7z", files);
  			
  			//decompress("D:\\Mathi\\NRIC_Components\\text.7z", new File("D:\\Mathi\\NRIC_Components\\1"));
  			
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch(Exception ex){
  			ex.printStackTrace();
  		}
    	
    }

    public static void compress(String name, File... files) throws IOException {
       /* try (SevenZOutputFile out = new SevenZOutputFile(new File(name))){
            for (File file : files){
                addToArchiveCompression(out, file, ".");
            }
        }*/
    	
    	SevenZOutputFile out = null;
    	try{
    		 out = new SevenZOutputFile(new File(name));
    		 for (File file : files){
                 addToArchiveCompression(out, file, ".");
             }
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}finally {
    		out.close();
    	}
    	
    }

    public static void decompress(String in, File destination) throws IOException {
        SevenZFile sevenZFile = new SevenZFile(new File(in));
        SevenZArchiveEntry entry;
        while ((entry = sevenZFile.getNextEntry()) != null){
            if (entry.isDirectory()){
                continue;
            }
            File curfile = new File(destination, entry.getName());
            File parent = curfile.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(curfile);
            byte[] content = new byte[(int) entry.getSize()];
            sevenZFile.read(content, 0, content.length);
            out.write(content);
            out.close();
        }
    }

    private static void addToArchiveCompression(SevenZOutputFile out, File file, String dir) throws IOException {
        String name = dir + File.separator + file.getName();
        if (file.isFile()){
            SevenZArchiveEntry entry = out.createArchiveEntry(file, name);
            out.putArchiveEntry(entry);

            FileInputStream in = new FileInputStream(file);
            byte[] b = new byte[1024];
            int count = 0;
            while ((count = in.read(b)) > 0) {
                out.write(b, 0, count);
            }
            out.closeArchiveEntry();

        } else if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null){
                for (File child : children){
                    addToArchiveCompression(out, child, name);
                }
            }
        } else {
            System.out.println(file.getName() + " is not supported");
        }
    }
}
