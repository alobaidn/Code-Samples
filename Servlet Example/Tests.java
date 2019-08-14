import static org.junit.Assert.*;

import org.junit.Test;
import java.net.URL;
import org.junit.Before;
import org.junit.Test;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class Tests {

    @Before
    public void prepare() {
        setBaseUrl("http://cs.gmu.edu:8080/offutt/servlet/check24online");
    }

    @Test
    public void test1() {
        beginAt("/");
        setTextField("value1", "10");
        setTextField("value2", "10");
        setTextField("value3", "2");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
     }

	   
	      @Test
	   	public void test2() {// rslt = a+b+c-d;
	   	  beginAt("/");
        setTextField("value1", "10");
        setTextField("value2", "10");
        setTextField("value3", "6");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
	   	}
	      @Test
	   	public void test200() {// rslt = a+b+c-d;
	   	   beginAt("/");
        setTextField("value1", "1");
        setTextField("value2", "1");
        setTextField("value3", "1");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Try another");
	   
	   	}
	      @Test
	   	public void test3() { 
	   	 beginAt("/");
        setTextField("value1", "10");
        setTextField("value2", "10");
        setTextField("value3", "2");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
	   	}
	      @Test
	   	public void test4() {
	   beginAt("/");
        setTextField("value1", "6");
        setTextField("value2", "4");
        setTextField("value3", "2");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
        	
	   	}

	      @Test
	     	public void test6() { //exception case
	     	  beginAt("/");
        setTextField("value1", "10");
        setTextField("value2", "10");
        setTextField("value3", "8");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
       
	     	}
	      @Test
	     	public void test7() {
	     	beginAt("/");
        setTextField("value1", "20");
        setTextField("value2", "20");
        setTextField("value3", "8");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
	     	}
	      @Test
	     	public void test8() {
	     	 beginAt("/");
        setTextField("value1", "10");
        setTextField("value2", "10");
        setTextField("value3", "2");
        setTextField("value4", "6");
        submit();
        assertTextPresent("Solution found:");
	     	}
	      
	      @Test
	     	public void test10() {
	     	 beginAt("/");
        setTextField("value1", "10");
        setTextField("value2", "16");
        setTextField("value3", "1");
        setTextField("value4", "1");
        submit();
        assertTextPresent("Solution found:");
	    
	     	}
	      @Test
	     	public void test11() {
	     	  beginAt("/");
        setTextField("value1", "10");
        setTextField("value2", "18");
        setTextField("value3", "2");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
	     	}
	      @Test
	     	public void test13() {//excep
	     	  beginAt("/");
        setTextField("value1", "10");
        setTextField("value2", "18");
        setTextField("value3", "12");
        setTextField("value4", "3");
        submit();
        assertTextPresent("Solution found:");
      }
	      @Test
	     	public void test14() {//ex
	     	 beginAt("/");
        setTextField("value1", "20");
        setTextField("value2", "30");
        setTextField("value3", "2");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
	     	}
	      @Test
	     	public void test15() {
	     	  beginAt("/");
        setTextField("value1", "10");
        setTextField("value2", "5");
        setTextField("value3", "2");
        setTextField("value4", "4");
        submit();
        assertTextPresent("Solution found:");
       }
	      @Test
	     	public void test16() {
	     	  beginAt("/");
        setTextField("value1", "10");
        setTextField("value2", "2");
        setTextField("value3", "1");
        setTextField("value4", "1");
        submit();
        assertTextPresent("Solution found:");
      }
	      @Test
	     	public void test17() {
	     	beginAt("/");
        setTextField("value1", "16");
        setTextField("value2", "5");
        setTextField("value3", "2");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	     	public void test18() {
	     	beginAt("/");
        setTextField("value1", "10");
        setTextField("value2", "2");
        setTextField("value3", "3");
        setTextField("value4", "1");
        submit();
       }
	      @Test
	     	public void test19() {
	     	  beginAt("/");
        setTextField("value1", "6");
        setTextField("value2", "3");
        setTextField("value3", "3");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
      }
	      @Test
	     	public void test20() {
	     	 beginAt("/");
        setTextField("value1", "3");
        setTextField("value2", "1");
        setTextField("value3", "3");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	     	public void test21() {//ex
	     	beginAt("/");
        setTextField("value1", "19");
        setTextField("value2", "10");
        setTextField("value3", "2");
        setTextField("value4", "4");
        submit();
        assertTextPresent("Solution found:");
       }  
	      @Test
	     	public void test22() {//ex
	     	 beginAt("/");
        setTextField("value1", "2");
        setTextField("value2", "2");
        setTextField("value3", "12");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	     	public void test23() {//ex
	     	beginAt("/");
        setTextField("value1", "6");
        setTextField("value2", "3");
        setTextField("value3", "12");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
       }
	      @Test
	     	public void test24() {//ex  rslt = (a+b)*(c/d);
	     	beginAt("/");
        setTextField("value1", "2");
        setTextField("value2", "2");
        setTextField("value3", "12");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
       }
	      @Test
	    	public void test25() {//ex   rslt = a+b/c+d;
	    	 beginAt("/");
        setTextField("value1", "10");
        setTextField("value2", "12");
        setTextField("value3", "3");
        setTextField("value4", "10");
        submit();
        assertTextPresent("Solution found:");
        }
	      /**** + / ? */
	      @Test
	   	public void test26() {//ex    rslt = (a+b)/(c+d);
	   	beginAt("/");
        setTextField("value1", "28");
        setTextField("value2", "20");
        setTextField("value3", "1");
        setTextField("value4", "1");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	     	public void test27() {//ex       rslt = (a+b)/c+d;
	     	 beginAt("/");
        setTextField("value1", "28");
        setTextField("value2", "20");
        setTextField("value3", "4");
        setTextField("value4", "12");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	    	public void test28() {//ex        rslt = a+b/c-d;
	    	 beginAt("/");
        setTextField("value1", "22");
        setTextField("value2", "12");
        setTextField("value3", "3");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	   	public void test29() {//ex       rslt = (a+b)/(c-d);
	   	 beginAt("/");
        setTextField("value1", "28");
        setTextField("value2", "20");
        setTextField("value3", "4");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
       }
	      @Test
	     	public void test30() {//ex       rslt = (a+b)/c-d;
	     	 beginAt("/");
        setTextField("value1", "30");
        setTextField("value2", "20");
        setTextField("value3", "2");
        setTextField("value4", "1");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	    	public void test31() {//ex    rslt = a+b/(c-d);
        beginAt("/");
        setTextField("value1", "14");
        setTextField("value2", "20");
        setTextField("value3", "3");
        setTextField("value4", "1");
        submit();
        assertTextPresent("Solution found:");
       }
	      @Test
	   	public void test32() {//ex          rslt = a+b/c*d;

	   	  beginAt("/");
        setTextField("value1", "4");
        setTextField("value2", "12");
        setTextField("value3", "3");
        setTextField("value4", "5");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	     	public void test33() {//ex         rslt = (a+b)/(c*d);

	     	  beginAt("/");
        setTextField("value1", "200");
        setTextField("value2", "40");
        setTextField("value3", "2");
        setTextField("value4", "5");
        submit();
        assertTextPresent("Solution found:");
       }
	      @Test
	    	public void test34() {//ex         rslt = (a+b)/c*d;

	    	 beginAt("/");
        setTextField("value1", "14");
        setTextField("value2", "2");
        setTextField("value3", "2");
        setTextField("value4", "3");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	   	public void test35() {//ex         rslt = a+b/(c*d);

	   	 beginAt("/");
        setTextField("value1", "22");
        setTextField("value2", "12");
        setTextField("value3", "2");
        setTextField("value4", "3");
        submit();
        assertTextPresent("Solution found:");
      }
	    //  @Test
	   	//public void test36() {//ex         rslt = a+b/c/d;

	   	  // StringWriter stringWriter = new StringWriter(); PrintWriter pw = new PrintWriter(stringWriter);
	   	//checkSolutions(22,12,2,3,pw);
	   	//String s = stringWriter.toString();
	   	//assertTrue(s.contains("Solution found: "));
	   	//}
	      @Test
	     	public void test37() {//ex         rslt = (a+b)/(c/d);

	     	 beginAt("/");
        setTextField("value1", "28");
        setTextField("value2", "20");
        setTextField("value3", "4");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	    	public void test38() {//ex           rslt = (a+b)/c/d;

	    	 beginAt("/");
        setTextField("value1", "90");
        setTextField("value2", "6");
        setTextField("value3", "2");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	   	public void test39() {//ex            rslt = a+b/(c/d);

	   	 beginAt("/");
        setTextField("value1", "17");
        setTextField("value2", "21");
        setTextField("value3", "12");
        setTextField("value4", "4");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	   	public void test40() {//ex            rslt = a-b+c+d;

	   	  beginAt("/");
        setTextField("value1", "20");
        setTextField("value2", "2");
        setTextField("value3", "4");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	   	public void test41() {//ex            rslt = a-(b+c+d);

	   	 beginAt("/");
        setTextField("value1", "30");
        setTextField("value2", "2");
        setTextField("value3", "3");
        setTextField("value4", "1");
        submit();
        assertTextPresent("Solution found:");
      }
	      @Test
	     	public void test42() {//            rslt = a-b+c-d;
beginAt("/");
        setTextField("value1", "26");
        setTextField("value2", "6");
        setTextField("value3", "7");
        setTextField("value4", "3");
        submit();
        assertTextPresent("Solution found:");
       }  
	      @Test
	    	public void test43() {//            rslt = a-(b+c)-d;

	    	beginAt("/");
        setTextField("value1", "30");
        setTextField("value2", "1");
        setTextField("value3", "2");
        setTextField("value4", "3");
        submit();
        assertTextPresent("Solution found:");
        }  
	      @Test
	   	public void test44() {//            rslt = a-((b+c)-d);

	   	beginAt("/");
        setTextField("value1", "26");
        setTextField("value2", "3");
        setTextField("value3", "2");
        setTextField("value4", "3");
        submit();
        assertTextPresent("Solution found:");
       }  
	      @Test
	   	public void test45() {          //  rslt = a-b+c*d;

	   	beginAt("/");
        setTextField("value1", "20");
        setTextField("value2", "2");
        setTextField("value3", "2");
        setTextField("value4", "3");
        submit();
        assertTextPresent("Solution found:");
       }
	      @Test
	   	public void test46() {          // rslt = (a-b+c)*d;

	   	  beginAt("/");
        setTextField("value1", "10");
        setTextField("value2", "2");
        setTextField("value3", "4");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
        }
	     
	      @Test
	    	public void test48() {   //ex       rslt = a-b+c/d;   
beginAt("/");
        setTextField("value1", "22");
        setTextField("value2", "2");
        setTextField("value3", "8");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	   	public void test49() {   //ex        rslt = (a-b+c)/d;   

	   beginAt("/");
        setTextField("value1", "52");
        setTextField("value2", "6");
        setTextField("value3", "2");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	   	public void test50() {   //ex         rslt = (a-(b+c))/d;  

	   	beginAt("/");
        setTextField("value1", "58");
        setTextField("value2", "2");
        setTextField("value3", "8");
        setTextField("value4", "2");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	   	public void test51() {   //    rslt = a-b-c+d;  

	   	 	beginAt("/");
        setTextField("value1", "22");
        setTextField("value2", "2");
        setTextField("value3", "3");
        setTextField("value4", "7");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	     	public void test52() {   //  rslt = a-(b-c)+d; 

	     	 	beginAt("/");
        setTextField("value1", "22");
        setTextField("value2", "4");
        setTextField("value3", "3");
        setTextField("value4", "3");
        submit();
        assertTextPresent("Solution found:");
        }
	      @Test
	    	public void test53() {   //  rslt = a-b-(c+d);

	    		beginAt("/");
        setTextField("value1", "28");
        setTextField("value2", "2");
        setTextField("value3", "1");
        setTextField("value4", "1");
        submit();
        assertTextPresent("Solution found:");
       }
	      @Test
	   	public void test54() {   //  rslt = a-(b-(c+d));

	   	  	beginAt("/");
        setTextField("value1", "28");
        setTextField("value2", "6");
        setTextField("value3", "1");
        setTextField("value4", "1");
        submit();
        assertTextPresent("Solution found:");
       }
	      @Test
	     	public void test56() {   // rslt = a-b-c-d;

	     		beginAt("/");
        setTextField("value1", "30");
        setTextField("value2", "6");
        setTextField("value3", "1");
        setTextField("value4", "1");
        submit();
        assertTextPresent("Solution found:");
        }
}
	   /*
	      rslt = a-(b-c)-d;
	      if (rslt == 24 &&  rslt == (float)a-((float)b-(float)c)-(float)d)
	         out.println ("Solution found: "+a+"-("+b+"-"+c+")-"+d+" = 24<BR>");
	      rslt = a-b-(c-d);
	      if (rslt == 24 &&  rslt == (float)a-(float)b-((float)c-(float)d))
	         out.println ("Solution found: "+a+"-"+b+"-("+c+"-"+d+") = 24<BR>");
	      rslt = a-(b-(c-d));
	      if (rslt == 24 &&  rslt == (float)a-((float)b-((float)c-(float)d)))
	         out.println ("Solution found: "+a+"-("+b+"-("+c+"-"+d+")) = 24<BR>");
	      rslt = a-((b-c)-d);
	      if (rslt == 24 &&  rslt == (float)a-(((float)b-(float)c)-(float)d))
	         out.println ("Solution found: "+a+"-(("+b+"-"+c+")-"+d+") = 24<BR>");

	      rslt = a-b-c*d;
	      if (rslt == 24 &&  rslt == (float)a-(float)b-(float)c*(float)d)
	         out.println ("Solution found: "+a+"-"+b+"-"+c+"*"+d+" = 24<BR>");
	      rslt = a-(b-c)*d;
	      if (rslt == 24 &&  rslt == (float)a-((float)b-(float)c)*(float)d)
	         out.println ("Solution found: "+a+"-("+b+"-"+c+")*"+d+" = 24<BR>");
	      rslt = a-b-(c*d);
	      if (rslt == 24 &&  rslt == (float)a-(float)b-((float)c*(float)d))
	         out.println ("Solution found: "+a+"-"+b+"-("+c+"*"+d+") = 24<BR>");
	      rslt = a-(b-(c*d));
	      if (rslt == 24 &&  rslt == (float)a-((float)b-((float)c*(float)d)))
	         out.println ("Solution found: "+a+"-("+b+"-("+c+"*"+d+")) = 24<BR>");
	      rslt = a-((b-c)*d);
	      if (rslt == 24 &&  rslt == (float)a-(((float)b-(float)c)*(float)d))
	         out.println ("Solution found: "+a+"-(("+b+"-"+c+")*"+d+") = 24<BR>");
	      rslt = (a-b-c)*d;
	      if (rslt == 24 &&  rslt == ((float)a-(float)b-(float)c)*(float)d)
	         out.println ("Solution found: ("+a+"-"+b+"-"+c+")*"+d+" = 24<BR>");
	      rslt = (a-(b-c))*d;
	      if (rslt == 24 &&  rslt == ((float)a-((float)b-(float)c))*(float)d)
	         out.println ("Solution found: ("+a+"-("+b+"-"+c+"))*"+d+" = 24<BR>");

	      try {
	         rslt = a-b-c/d;
	         if (rslt == 24 &&  rslt == (float)a-(float)b-(float)c/(float)d)
	            out.println ("Solution found: "+a+"-"+b+"-"+c+"/"+d+" = 24<BR>");
	      } catch (ArithmeticException e) { }
	      try {
	         rslt = a-(b-c)/d;
	         if (rslt == 24 &&  rslt == (float)a-((float)b-(float)c)/(float)d)
	            out.println ("Solution found: "+a+"-("+b+"-"+c+")/"+d+" = 24<BR>");
	      } catch (ArithmeticException e) { }
	      try {
	         rslt = a-b-(c/d);
	         if (rslt == 24 &&  rslt == (float)a-(float)b-((float)c/(float)d))
	            out.println ("Solution found: "+a+"-"+b+"-("+c+"/"+d+") = 24<BR>");
	      } catch (ArithmeticException e) { }
	      try {
	         rslt = a-(b-(c/d));
	         if (rslt == 24 &&  rslt == (float)a-((float)b-((float)c/(float)d)))
	            out.println ("Solution found: "+a+"-("+b+"-("+c+"/"+d+")) = 24<BR>");
	      } catch (ArithmeticException e) { }
	      try {
	         rslt = a-((b-c)/d);
	         if (rslt == 24 &&  rslt == (float)a-(((float)b-(float)c)/(float)d))
	            out.println ("Solution found: "+a+"-(("+b+"-"+c+")/"+d+") = 24<BR>");
	      } catch (ArithmeticException e) { }
	      try {
	         rslt = (a-b-c)/d;
	         if (rslt == 24 &&  rslt == ((float)a-(float)b-(float)c)/(float)d)
	            out.println ("Solution found: ("+a+"-"+b+"-"+c+")/"+d+" = 24<BR>");
	      } catch (ArithmeticException e) { }
	      try {
	         rslt = (a-(b-c))/d;
	         if (rslt == 24 &&  rslt == ((float)a-((float)b-(float)c))/(float)d)
	            out.println ("Solution found: ("+a+"-("+b+"-"+c+"))/"+d+" = 24<BR>");
	      } catch (ArithmeticException e) { }

	      /**** - * ? 
	      rslt = a-b*c+d;
	      if (rslt == 24 &&  rslt == (float)a-(float)b*(float)c+(float)d)
	         out.println ("Solution found: "+a+"-"+b+"*"+c+"+"+d+" = 24<BR>");
	      rslt = a-(b*c)+d;
	      if (rslt == 24 &&  rslt == (float)a-((float)b*(float)c)+(float)d)
	         out.println ("Solution found: "+a+"-("+b+"*"+c+")+"+d+" = 24<BR>");
	      rslt = a-b*(c+d);
	      if (rslt == 24 &&  rslt == (float)a-(float)b*((float)c+(float)d))
	         out.println ("Solution found: "+a+"-"+b+"*("+c+"+"+d+") = 24<BR>");
	      rslt = a-(b*(c+d));
	      if (rslt == 24 &&  rslt == (float)a-((float)b*((float)c+(float)d)))
	         out.println ("Solution found: "+a+"-("+b+"*("+c+"+"+d+")) = 24<BR>");
	      rslt = a-((b*c)+d);
	      if (rslt == 24 &&  rslt == (float)a-(((float)b*(float)c)+(float)d))
	         out.println ("Solution found: "+a+"-(("+b+"*"+c+")+"+d+") = 24<BR>");
	      rslt = (a-b*c)+d;
	      if (rslt == 24 &&  rslt == ((float)a-(float)b*(float)c)+(float)d)
	         out.println ("Solution found: ("+a+"-"+b+"*"+c+")+"+d+" = 24<BR>");
	      rslt = (a-(b*c))+d;
	      if (rslt == 24 &&  rslt == ((float)a-((float)b*(float)c))+(float)d)
	         out.println ("Solution found: ("+a+"-("+b+"*"+c+"))+"+d+" = 24<BR>");

	      rslt = a-b*c-d;
	      if (rslt == 24 &&  rslt == (float)a-(float)b*(float)c-(float)d)
	         out.println ("Solution found: "+a+"-"+b+"*"+c+"-"+d+" = 24<BR>");
	      rslt = a-(b*c)-d;
	      if (rslt == 24 &&  rslt == (float)a-((float)b*(float)c)-(float)d)
	         out.println ("Solution found: "+a+"-("+b+"*"+c+")-"+d+" = 24<BR>");
	      rslt = a-b*(c-d);
	      if (rslt == 24 &&  rslt == (float)a-(float)b*((float)c-(float)d))
	         out.println ("Solution found: "+a+"-"+b+"*("+c+"-"+d+") = 24<BR>");
	      rslt = a-(b*(c-d));
	      if (rslt == 24 &&  rslt == (float)a-((float)b*((float)c-(float)d)))
	         out.println ("Solution found: "+a+"-("+b+"*("+c+"-"+d+")) = 24<BR>");
	      rslt = a-((b*c)-d);
	      if (rslt == 24 &&  rslt == (float)a-(((float)b*(float)c)-(float)d))
	         out.println ("Solution found: "+a+"-(("+b+"*"+c+")-"+d+") = 24<BR>");
	      rslt = (a-b*c)-d;
	      if (rslt == 24 &&  rslt == ((float)a-(float)b*(float)c)-(float)d)
	         out.println ("Solution found: ("+a+"-"+b+"*"+c+")-"+d+" = 24<BR>");
	      rslt = (a-(b*c))-d;
	      if (rslt == 24 &&  rslt == ((float)a-((float)b*(float)c))-(float)d)
	         out.println ("Solution found: ("+a+"-("+b+"*"+c+"))-"+d+" = 24<BR>");

	      rslt = a-b*c*d;
	      if (rslt == 24 &&  rslt == (float)a-(float)b*(float)c*(float)d)
	         out.println ("Solution found: "+a+"-"+b+"*"+c+"*"+d+" = 24<BR>");
	      rslt = a-(b*c)*d;
	      if (rslt == 24 &&  rslt == (float)a-((float)b*(float)c)*(float)d)
	         out.println ("Solution found: "+a+"-("+b+"*"+c+")*"+d+" = 24<BR>");
	      rslt = a-b*(c*d);
	      if (rslt == 24 &&  rslt == (float)a-(float)b*((float)c*(float)d))
	         out.println ("Solution found: "+a+"-"+b+"*("+c+"*"+d+") = 24<BR>");
	      rslt = a-(b*(c*d));
	      if (rslt == 24 &&  rslt == (float)a-((float)b*((float)c*(float)d)))
	         out.println ("Solution found: "+a+"-("+b+"*("+c+"*"+d+")) = 24<BR>");
	      rslt = a-((b*c)*d);
	      if (rslt == 24 &&  rslt == (float)a-(((float)b*(float)c)*(float)d))
	         out.println ("Solution found: "+a+"-(("+b+"*"+c+")*"+d+") = 24<BR>");
	      rslt = (a-b*c)*d;
	      if (rslt == 24 &&  rslt == ((float)a-(float)b*(float)c)*(float)d)
	         out.println ("Solution found: ("+a+"-"+b+"*"+c+")*"+d+" = 24<BR>");
	      rslt = (a-(b*c))*d;
	      if (rslt == 24 &&  rslt == ((float)a-((float)b*(float)c))*(float)d)
	         out.println ("Solution found: ("+a+"-("+b+"*"+c+"))*"+d+" = 24<BR>");

	      try {
	         rslt = a-b*c/d;
	         if (rslt == 24 &&  rslt == (float)a-(float)b*(float)c/(float)d)
	            out.println ("Solution found: "+a+"-"+b+"*"+c+"+"+d+" = 24<BR>");
	      } catch (ArithmeticException e) { }
	      try {
	         rslt = a-(b*c)/d;
	         if (rslt == 24 &&  rslt == (float)a-((float)b*(float)c)/(float)d)
	            out.println ("Solution found: "+a+"-("+b+"*"+c+")/"+d+" = 24<BR>");
	      } catch (ArithmeticException e) { }
	      try {
	         rslt = a-b*(c/d);
	         if (rslt == 24 &&  rslt == (float)a-(float)b*((float)c/(float)d))
	            out.println ("Solution found: "+a+"-"+b+"*("+c+"/"+d+") = 24<BR>");
	      } catch (ArithmeticException e) { }
	      try {
	         rslt = a-(b*(c/d));
	         if (rslt == 24 &&  rslt == (float)a-((float)b*((float)c/(float)d)))
	            out.println ("Solution found: "+a+"-("+b+"*("+c+"/"+d+")) = 24<BR>");
	      } catch (ArithmeticException e) { }
	   try {
	         rslt = a-((b*c)/d);
	         if (rslt == 24 &&  rslt == (float)a-(((float)b*(float)c)/(float)d))
	            out.println ("Solution found: "+a+"-(("+b+"*"+c+")/"+d+") = 24<BR>");
	      } catch (ArithmeticException e) { }
	      try {
	         rslt = (a-b*c)/d;
	         if (rslt == 24 &&  rslt == ((float)a-(float)b*(float)c)/(float)d)
	            out.println ("Solution found: ("+a+"-"+b+"*"+c+")/"+d+" = 24<BR>");
	      } catch (ArithmeticException e) { }
	      try {
	         rslt = (a-(b*c))/d;
	         if (rslt == 24 &&  rslt == ((float)a-((float)b*(float)c))/(float)d)
	            out.println ("Solution found: ("+a+"-("+b+"*"+c+"))/"+d+" = 24<BR>");
	      } catch (ArithmeticException e) { }

	      /**** - / ? 
	      try {
	         rslt = a-b/c+d;
	         if (rslt == 24 &&  rslt == (float)a-(float)b/(float)c+(float)d)
	            out.println ("Solution found: "+a+"-"+b+"/"+c+"+"+d+" = 24<BR>");
	      } catch (ArithmeticException e) { }
	      try {
	         rslt = a-(b/c)+d;
	         if (rslt == 24 &&  rslt == (float)a-((float)b/(float)c)+(float)d)
	            out.println ("Solution found: "+a+"-("+b+"/"+c+")+"+d+" = 24<BR>");
	      } catch (ArithmeticException e) { }
	      try {
	         rslt = a-b/(c+d);
	         if (rslt == 24 &&  rslt == (float)a-(float)b/((float)c+(float)d))
	            out.println ("Solution found: "+a+"-"+b+"/("+c+"+"+d+") = 24<BR>");
	      } catch (ArithmeticException e) { }
	      try {
	         rslt = a-(b/(c+d));
	         if (rslt == 24 &&  rslt == (float)a-((float)b/((float)c+(float)d)))
	            out.println ("Solution found: "+a+"-("+b+"/("+c+"+"+d+")) = 24<BR>");
	      } catch (ArithmeticException e) { }
	      try {
	         rslt = a-((b/c)+d);
	         if (rslt == 24 &&  rslt == (float)a-(((float)b/(float)c)+(float)d))
	            out.println ("Solution found: "+a+"-(("+b+"/"+c+")+"+d+") = 24<BR>");
	      } catch (ArithmeticException e) { }
	      try {
	         rslt = (a-b/c)+d;
	         if (rslt == 24 &&  rslt == ((float)a-(float)b/(float)c)+(float)d)
	            out.println ("Solution found: ("+a+"-"+b+"/"+c+")+"+d+" = 24<BR>");
	      } catch (ArithmeticException e) { }
	      try {
	         rslt = (a-(b/c))+d;
	         if (rslt == 24 &&  rslt == ((float)a-((float)b/(float)c))/(float)d)
	            out.println ("Solution found: ("+a+"-("+b+"/"+c+"))/"+d+" = 24<BR>");
	      } catch (ArithmeticException e) { }

	   */

	//   }



