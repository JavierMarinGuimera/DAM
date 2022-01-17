package GetFilesTest;

import java.io.IOException;
import java.net.MalformedURLException;

public class TestResourceSaver {

   /**
    *
    * @param args the command line arguments
    *
    */
   public static void main(String[] args) throws MalformedURLException, IOException {

       ResourceSaver saver = new ResourceSaver();

       saver.addResourceType(".*/pdf", "pdfs");
       saver.addResourceType("image/.*", "images");
       saver.addResourceType("audio/.*", "audios");
       saver.addResourceType(".*zip", "zips");
       saver.addResourceType("text/plain.*", "txts");
       saver.addResourceType(".*html", "htmls");
       saver.addResourceType("video/.*", "videos");

       saver.createFolderTree();
	   save("ftp://mirror.vexxhost.com/apache/activemq/apache-nms/1.6.0/Apache.NMS-1.6.0-src.zip", saver);
	   save("http://mirror.team-cymru.org/ubuntu/indices/override.breezy-backports.extra.main", saver);
	   save("http://www.nic.funet.fi/pub/gnu/ftp.gnu.org/pub/gnu/Licenses/lgpl-2.1.txt", saver);
	   save("ftp://ftp.man.lodz.pl/pub/security/README.html", saver);
	   save("http://www.uscis.gov/files/form/i-9.pdf", saver);
	   save("http://farm8.staticflickr.com/7076/7301495298_ee121d2013_s_d.jpg", saver);
	   save("http://www.mediacollege.com/downloads/sound-effects/city/traffic-02.mp3", saver);
	   save("http://archive.org/download/TheCaseOfTheKangarooKid1963/TheKangarooKid.mp4", saver);
   }

   public static void save(String resource, ResourceSaver saver) {
       try {
           saver.saveResource(resource);
       } catch (Exception ex) {
           System.out.println("Error de tipus desconegut");
       }
   }
}