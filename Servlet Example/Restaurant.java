package nalobaid;
import java.io.*;
import java.util.*;
public class Restaurant
{
String name;
String loc;
String opinion;
//String rating;
String date;
String hated;
String loved;
public Restaurant(String n, String l, String o, String d, String h, String lov)
{
name =n;
loc = l;
opinion = o;
//rating = r;
date =d;
hated = h;
loved = lov;
}
public String getName()
{
return name;
}
public String getLoc()
{
return loc;
}
//public String getRating()
//{
//return rating;
//}
public String getDate()
{
return date;
}
public String getHated()
{
return hated;
}
public String getLoved()
{
return loved;
}
public String getOpinion()
{
return opinion;
}
}