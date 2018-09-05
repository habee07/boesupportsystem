import java.io.*;
import java.util.*;

public class outcome1 {

     /* 
      * Header of outcome1.csv-
  		"Calendar Instance Year,fca04484,Registration Status,Program Code,Progress Outcome Type,Progress Outcome Type Description,
  		Year of Study,Average Marks,Course Attempt Status,Enrolled Credits,Achieved Credits"
  		*/
	
	public static class outcome {
		
		private String calendarYear;
		private String fca04484;
		private String regStatus;
		private String programCode;
		private String progressOutcomeType;
		private String progressOutcomeDesc;
		private int YOS;
		private int avgMarks;
		private String courseAttemptStatus;
		private int enrolledCreds;
		private int achievedCreds;
		
		public String getCalendarYear() {
            return calendarYear;
        }

        public void setCalendarYear(String calendarYear) {
            this.calendarYear = calendarYear;
        }

        public String getFca04484() {
            return fca04484;
        }

        public void setFca04484(String fca04484) {
            this.fca04484 = fca04484;
        }

        public String getRegStatus() {
            return regStatus;
        }

        public void setRegStatus(String regStatus) {
            this.regStatus = regStatus;
        }

        public String getProgramCode() {
            return programCode;
        }

        public void setProgramCode(String programCode) {
            this.programCode = programCode;
        }

        public String getProgressOutcomeType() {
            return progressOutcomeType;
        }

        public void setProgressOutcomeType (String progressOutcomeType) {
            this.progressOutcomeType = progressOutcomeType;
        }

        public String getProgressOutcomeDesc() {
            return progressOutcomeDesc;
        }

        public void setProgressOutcomeDesc (String progressOutcomeDesc) {
            this.progressOutcomeDesc = progressOutcomeDesc;
        }
        
        public int getYOS() {
            return YOS;
        }

        public void setYOS (int YOS) {
            this.YOS = YOS;
        }
        
        public int getAvgMarks() {
            return avgMarks;
        }

        public void setAvgMarks (int avgMarks) {
            this.avgMarks = avgMarks;
        }

		public String getCourseAttemptStatus() {
			return courseAttemptStatus;
		}

		public void setCourseAttemptStatus(String courseAttemptStatus) {
			this.courseAttemptStatus = courseAttemptStatus;
		}

		public int getEnrolledCreds() {
			return enrolledCreds;
		}

		public void setEnrolledCreds(int enrolledCreds) {
			this.enrolledCreds = enrolledCreds;
		}

		public int getAchievedCreds() {
			return achievedCreds;
		}

		public void setAchievedCreds(int achievedCreds) {
			this.achievedCreds = achievedCreds;
		}	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}