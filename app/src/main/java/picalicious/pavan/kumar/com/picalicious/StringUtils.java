package picalicious.pavan.kumar.com.picalicious;

public class StringUtils {





        public static String getToUpper(String temp)
        {
            if(temp!=null && !temp.equals("")){
                temp=temp.substring(0,1).toUpperCase()+temp.substring(1);
                return temp.trim();
            }
            return temp;
        }




    }
