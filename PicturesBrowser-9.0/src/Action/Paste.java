package Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import Action.Copy;
import Control.ImageBoxButton;
import Control.PicturePane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;


public class Paste {
    public Paste()
    {
		Clipboard clipboard = Clipboard.getSystemClipboard();//���ϵͳ������
		@SuppressWarnings("unchecked")
		List<File> files = (List<File>) (clipboard.getContent(DataFormat.FILES));//��ü�������ļ�
		if (files.size() <= 0)
		{
			Alert alert = new Alert(AlertType.INFORMATION);
	         alert.titleProperty().set("����");
	         alert.headerTextProperty().set("ճ��ʧ��");
	         alert.showAndWait();
		}
		if (ImageBoxButton.getCopyPictures().size() > 0)
		{
			File first = files.get(0);
			//�����а������Ƿ���Ҫ���Ƶ�����
			if(first.getParentFile().getAbsolutePath().compareTo(PicturePane.filePath) == 0)
			{
				//�������ѡ�������
				ImageBoxButton.clearSelected();
				ImageBoxButton.getCopyPictures().clear();
				ImageBoxButton.getSelectedPictureFiles().clear();
				clipboard.clear();
				return;	
			}
		}
		
		for(File oldFile : files) 
		{
			File oldFiles=new File(Copy.dir+File.separator+oldFile.getName());
			String newName = Pasterename(PicturePane.filePath,oldFiles.getName());
			File newFile = new File(PicturePane.filePath+File.separator+newName);
			try
			{
				//�������ļ����ļ�ϵͳ
				newFile.createNewFile();
			} 
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(newFile.exists()) 
			{
				try 
				{
					//ͼƬ���ݸ���
					copyFile(oldFiles,newFile);
				} 
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			try 
			{
				//ͬ��������뵽ͼƬ������
				PicturePane.flowPane.getChildren().add(new ImageBoxButton("file:"+newFile.getAbsolutePath(),newFile.getName()).getImageLabel() );
			    
			} 
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("����");
			}
			if(ImageBoxButton.getCopyPictures().size()>0)
			{
				oldFile.delete();
			}
			}
		   //����ˢ��ͼƬ����
		    PicturePane.getPicture(PicturePane.file);
	}
    //���ֽ�������
	private void copyFile(File fromFile, File toFile) throws IOException
	{
		FileInputStream inputStream = new FileInputStream(fromFile);
		FileOutputStream outputStream = new FileOutputStream(toFile);
		byte[] b = new byte[1024];
		int byteRead;
		while ((byteRead = inputStream.read(b)) > 0) {
			outputStream.write(b, 0, byteRead);
		}
		inputStream.close();
		outputStream.close();
		
	}
	//ճ���ļ������޸ģ�+"����"
	private String Pasterename(String theFilePath, String name)
	{
		String newName = name;
		File fatherPathFile = new File(theFilePath);
		File[] filesInFatherPath = fatherPathFile.listFiles();
		for (File fileInFatherPath : filesInFatherPath) {
			String fileName = fileInFatherPath.getName();
			int cmp = newName.compareTo(fileName);
			if (cmp == 0) {
				String str = null;
				int end = newName.lastIndexOf("."), start = newName.lastIndexOf("_����");
				if (start != -1) {
					str = newName.substring(start, end);
					int num = 1;
					try {
						num = Integer.parseInt(str.substring(str.lastIndexOf("_����") + 3)) + 1;
						int cnt = 0, d = num - 1;
						while (d != 0) {
							d /= 10;
							cnt++;
						}
						newName = newName.substring(0, end - cnt) + num + newName.substring(end);
					} catch (Exception e) {
						newName = newName.substring(0, end) + "_����1" + newName.substring(end);
					}

				} else {
					newName = newName.substring(0, end) + "_����1" + newName.substring(end);
				}
			}
		}
		return newName;
	}
    }


