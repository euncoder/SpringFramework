package mul.cam.a.util;

import java.util.Date;

public class PdsUtil {
	// file명 -> 변경(time)
	//ex) myfile.txt -> 34245632.txt
							//오리지널파일이름을 새파일로
	public static String getNewFileName(String filename) {
		String newfilename="";
		String fpost="";
		
			//**.점부터 시작해서 확장자명까지얻어오기** ex)확장자명이 없는 데이터 mydata .이없어서 -1이나옴
		if(filename.indexOf('.') >= 0) { //확장자명이 있을때(파일명을 바꿔서 리턴)
			fpost = filename.substring(filename.indexOf('.')); //시작위치 알리기 .txt
			newfilename = new Date().getTime() + fpost; //fpost = 숫자 + 문자 (= 34245632 + .txt)
		}else { // 확장자 명이 없을때
			newfilename = new Date().getTime() + ".back"; //확장자명을 붙혀서 저장(이름 암케나 지었음)
		}
		return newfilename;
	}
	
}
