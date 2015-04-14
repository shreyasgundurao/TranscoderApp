/**
 * 
 */
package junk;

import java.io.File;

/**
 * @author Shreyas
 *
 */
public class MediaInfoExtractor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String fileName   = "C:\\shreyas\\SJSU-Studies\\Semester3\\Cloud-281-Prof.Larkin\\Project\\data\\sample2.avi";
		File file         = new File(fileName);
		
		System.setProperty("java.library.path", "MediaInfo.dll");
		
		MediaInfo info    = new MediaInfo();
		info.open(file);

		String format     = info.get(MediaInfo.StreamKind.Video, 1, "Format", 
		                        MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
		/*String bitRate       = info.get(MediaInfo.StreamKind.Video, 1, "BitRate", 
		                        MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
		String frameRate   = info.get(MediaInfo.StreamKind.Video, i, "FrameRate", 
		                        MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
		String width       = info.get(MediaInfo.StreamKind.Video, i, "Width", 
		                        MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);

		String audioBitrate  = info.get(MediaInfo.StreamKind.Audio, i, "BitRate", 
		                        MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
		String audioChannels = info.get(MediaInfo.StreamKind.Audio, i, "Channels", 
		                        MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
*/
		info.close();
		System.out.println("Format:  "+ format);
	}

}
