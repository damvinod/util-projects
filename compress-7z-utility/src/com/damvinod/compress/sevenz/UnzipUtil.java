package com.damvinod.compress.sevenz;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

public class UnzipUtil {

	public static void main(String[] args) {
		
		try {
			String classpath = System.getProperty("user.dir");
			File inputfile  = new File (classpath+ File.separator + "jre8.7z");
			String outputFileLocation = classpath + File.separator + "jre8";
			unzip7zFile(inputfile, outputFileLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void unzip7zFile(File inputfile, String outputFileLocation)
			throws Exception {
		if (!inputfile.exists())
			return;

		deCompress7z(inputfile.getAbsolutePath(), outputFileLocation);
		deCompressLibFolders(outputFileLocation);

		renameFileAndUnzipit(outputFileLocation);
	}

	public static void deCompress7z(String inputfile, String outputFolder)
			throws Exception {

		SevenZFile sevenZFile = new SevenZFile(new File(inputfile));
		SevenZArchiveEntry entry = sevenZFile.getNextEntry();

		while (entry != null) {

			String fileName = entry.getName();
			File newFile = new File(outputFolder + File.separator + fileName);

			// System.out.println("file unzip : "+ newFile.getAbsoluteFile());

			// create all non exists folders
			// else you will hit FileNotFoundException for compressed folder
			new File(newFile.getParent()).mkdirs();

			FileOutputStream out = new FileOutputStream(newFile);

			byte[] content = new byte[(int) entry.getSize()];

			int length;
			while ((length = sevenZFile.read(content, 0, content.length)) > 0) {
				out.write(content, 0, length);
			}

			out.close();

			entry = sevenZFile.getNextEntry();

		}
		sevenZFile.close();

	}

	public static void deCompressLibFolders(String outputFolder) throws Exception {

		File file = new File(outputFolder + "\\" + "lib");

		ArrayList<File> listof7zFiles = new ArrayList<File>(Arrays.asList(file
				.listFiles()));

		for (File file7z : listof7zFiles) {

			String fileName = file7z.getName();

			String filePath = file7z.getAbsolutePath();

			deCompress7z(filePath, (outputFolder + "\\"));

			file7z.delete();

		}
	}

	private static void renameFileAndUnzipit(String outputfileLocation) {

		try {

			File oldFile = new File(outputfileLocation
					+ "\\lib\\security.zipppp");

			File renameFile = new File(outputfileLocation
					+ "\\lib\\security.zip");

			if (oldFile.exists()) {
				oldFile.renameTo(renameFile);
			}

			unZipIt(outputfileLocation + "\\lib\\security.zip",
					outputfileLocation + "\\lib\\", outputfileLocation);

			renameFile.delete();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void unZipIt(String zipFile, String outputFolder,
			String output_folder) {

		byte[] buffer = new byte[1024];

		try {

			// create output directory is not exists
			File folder = new File(output_folder + "\\lib");
			if (!folder.exists()) {
				folder.mkdir();
			}

			// get the zip file content
			ZipInputStream zis = new ZipInputStream(
					new FileInputStream(zipFile));
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {

				String fileName = ze.getName();
				File newFile = new File(outputFolder + File.separator
						+ fileName);

				System.out.println("file unzip : " + newFile.getAbsoluteFile());

				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();

			System.out.println("Done");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
