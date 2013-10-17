import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



public class Zipf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Song> songs = new ArrayList<Song>();
		Scanner in = new Scanner(new BufferedInputStream(System.in));
		PrintWriter writer = new PrintWriter(System.out);
		long fi, zi;
		double qi;
		String si;
		int trackCounter = 1;
		
		//parse album length and favorite track count
		int albumLength = in.nextInt();
		int topTracksNum = in.nextInt();
		
		fi = in.nextLong();
		si = in.next();
		zi = fi;
		if(zi == 0) zi = 1;
		qi = (double)fi;
		songs.add(new Song(fi,zi,si,qi,trackCounter++));
				
		while(in.hasNext()){
			fi = in.nextLong();
			si = in.next();
			zi = songs.get(0).getZi()/trackCounter;
			//qi = (double)fi/(double)zi;
			qi = (double)fi * (double)trackCounter;
			songs.add(new Song(fi,zi,si,qi,trackCounter++));
		}
	
		Collections.sort(songs);
		
//		for(int i = 0; i < topTracksNum; i++){
//			int highest = 0;
//			
//			for(int j = 1; j < songs.size(); j++){
//				if(songs.get(j).getQi() > songs.get(highest).getQi()){
//					highest = j;
//				}
//			}
//				
//			//System.out.println(songs.remove(highest).getSi());
//	        writer.println(songs.remove(highest).getSi());
//	        
//		}
		
		for(int i = 0; i < topTracksNum; i++){
			writer.println(songs.get(i).getSi());
		}
		writer.flush();
		writer.close();
	}

	public static class Song implements Comparable<Song>{
		private long fi;
		private long zi;
		private String si;
		private double qi;
		private int trackNum;

		public Song(long fi, long zi, String si, double qi, int trackNum){
			this.fi = fi;
			this.zi = zi;
			this.si = si;
			this.qi = qi;
			this.trackNum = trackNum;
		}

		@Override
		public int compareTo(Song other) {
			// TODO Auto-generated method stub
			if(qi < other.getQi()){
				return 1;
			}
			
			if(qi > other.getQi()){
				return -1;
			}
			
			if(trackNum < other.getTrackNum()){
				return -1;
			}
			return 1;
		}

		public long getFi() {
			return fi;
		}

		public void setFi(long fi) {
			this.fi = fi;
		}

		public long getZi() {
			return zi;
		}

		public void setZi(long zi) {
			this.zi = zi;
		}

		public String getSi() {
			return si;
		}

		public void setSi(String si) {
			this.si = si;
		}

		public double getQi() {
			return qi;
		}

		public void setQi(double qi) {
			this.qi = qi;
		}

		public int getTrackNum() {
			return trackNum;
		}

		public void setTrackNum(int trackNum) {
			this.trackNum = trackNum;
		}

	}
}
