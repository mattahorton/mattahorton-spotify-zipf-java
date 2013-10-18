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
		//Album Data Structure
		ArrayList<Song> songs = new ArrayList<Song>();

		//stdin and stdout setup
		Scanner in = new Scanner(new BufferedInputStream(System.in));
		PrintWriter writer = new PrintWriter(System.out);

		//song properties
		long fi, zi;
		double qi;
		String si;
		int trackCounter = 1;
		
		//parse album length and favorite track count
		int albumLength = in.nextInt();
		int topTracksNum = in.nextInt();
		
		//Instantiate first track on album
		fi = in.nextLong();
		si = in.next();
		zi = fi;
		if(zi == 0) zi = 1;
		qi = (double)fi; //first track is basis for album quality
		songs.add(new Song(fi,zi,si,qi,trackCounter++));
				
		//instantiate song objects for rest of album
		while(in.hasNext()){
			fi = in.nextLong();
			si = in.next();
			zi = songs.get(0).getZi()/trackCounter;
			qi = (double)fi * (double)trackCounter;
			songs.add(new Song(fi,zi,si,qi,trackCounter++));
		}
	
		//Using the Comparable interface's compareTo() method, sort the album by quality
		Collections.sort(songs);
		

		//Print out require number of top tracks
		for(int i = 0; i < topTracksNum; i++){
			writer.println(songs.get(i).getSi());
		}

		//flush and close writer
		writer.flush();
		writer.close();
	}

	/*
	/ Song:
	/ Implementation of the Comparable<T> interface
	/ Takes advantage of compareTo() to be sorted by Collections.sort()
	*/

	public static class Song implements Comparable<Song>{
		//Song property instance variables
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

			// returns 1 if quality is greater than the quality of the other object.
			// returns -1 if quality is less than the quality of the other object.
			// returns -1 if tracks have same quality and the current track is 
			// an earlier track on the album.

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

		//Getters and Setters

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
