package com.blackbear.hackerearth;

import java.util.HashMap;

class GetOutputData{
    public StringBuilder extractAttachmentSearchContextFromString (String problem)
    {
        String to="any";
        String todate="Any";
        String from="any";
        String fromdate="Any";
        String hasAttachments="Any";
        String attachmentType="Any";
        String attachmentSize="Any";
        String attachmentName="Any";
        String subject="any";
        String cc="any";

        HashMap<String, String> attname = new HashMap<>();
        attname.put("named","copy");
        attname.put("name","copy");
        attname.put("called","copy");

        HashMap<String, Integer> dateh = new HashMap<>();
        dateh.put("yesterday",1);
        dateh.put("yesterday's",1);
        dateh.put("today",0);
        dateh.put("today's",0);
        dateh.put("day before yesterday",2);


        HashMap<String, String> mailsub = new HashMap<>();
        mailsub.put("regarding","copy");
        mailsub.put("as","copy");
        mailsub.put("on","copy");
        mailsub.put("about","copy");

        HashMap<String, String> att = new HashMap<>();
        att.put("attachment","true");
        att.put("attachments","true");
        HashMap<String, String> noatt = new HashMap<>();
        noatt.put("no attachment","true");
        noatt.put("no attachments","true");
        noatt.put("without any attachment","true");
        noatt.put("without any attachments","true");

        HashMap<String, String> present = new HashMap<>();

        present.put("pdf","AttachmentType");
        present.put("pdf's","AttachmentType");
        present.put("txt's","AttachmentType");
        present.put("ppt","AttachmentType");
        present.put("xls","AttachmentType");
        present.put("txt","AttachmentType");
        present.put("pdfs","AttachmentType");
        present.put("ppts","AttachmentType");
        present.put("txts","AttachmentType");
        present.put("mp3","AttachmentType");
        present.put("wav","AttachmentType");
        present.put("mp3s","AttachmentType");
        present.put("zip","AttachmentType");
        present.put("rar","AttachmentType");
        present.put("zips","AttachmentType");
        present.put("rars","AttachmentType");
        present.put("sql","AttachmentType");
        present.put("xml","AttachmentType");
        present.put("apk","AttachmentType");
        present.put("jar","AttachmentType");
        present.put("bin","AttachmentType");
        present.put("java","AttachmentType");
        present.put("jpg","AttachmentType");
        present.put("jpeg","AttachmentType");
        present.put("png","AttachmentType");
        present.put("gif","AttachmentType");
        present.put("html","AttachmentType");
        present.put("php","AttachmentType");
        present.put("sql","AttachmentType");
        present.put("ppx","AttachmentType");
        present.put("xlr","AttachmentType");
        present.put("xlsx","AttachmentType");
        present.put("mp4","AttachmentType");
        present.put("3gp","AttachmentType");
        present.put("wmv","AttachmentType");
        present.put("mkv","AttachmentType");
        present.put("doc","AttachmentType");
        present.put("docs","AttachmentType");
        present.put("docx","AttachmentType");
        present.put("audio","AttachmentType");
        present.put("audios","AttachmentType");
        present.put("video","AttachmentType");
        present.put("videos","AttachmentType");
        present.put("video's","AttachmentType");
        present.put("audio's","AttachmentType");

        String str=(" "+problem+" ").toLowerCase();

        String operate ="";String previous="";
        char pre=',';
        for(int r=0;r<str.length();r++)
        {
            char p= str.charAt(r);
            if (!  (p==' '||p=='.'||p==',')  )
            {
                operate =operate+ p;

            }
            else
            {
                if(attname.containsKey(operate))
                {
                    String nameat="";
                    for(int rr=r;rr<str.length();rr++)
                    {
                        char pp= str.charAt(rr);
                        if (!  (pp==' '||pp=='.'||pp==',')  )
                        {
                            nameat =nameat+ pp;
                        }
                        else
                        {
                            hasAttachments="Yes";
                            attachmentName=nameat;
                        }
                    }//for
                }//if
                if(mailsub.containsKey(operate))
                {
                    String nameat="";int c=0;
                    for(int rr=r;rr<str.length();rr++)
                    {
                        char pp= str.charAt(rr);
                        if (!  (pp==' '||pp=='.'||pp==',')  )
                        {
                            nameat =nameat+ pp;
                        }
                        else
                        {c++;

                            if(nameat.compareTo("3rd")==0)
                            {
                                subject="Any";
                            }
                            else
                            {
                                subject=nameat;
                            }
                            if(c==2)
                            {
                                break;
                            }
                        }
                    }//for

                }//if
                if(dateh.containsKey(operate))
                {
                    if(dateh.get(operate)==0)
                    {
                        fromdate="Today";
                    }

                    else
                    {
                        fromdate="Today-"+dateh.get(operate);
                    }
                    todate="Today";
                }
                if(present.containsKey(operate))
                {
                    attachmentType=operate;
                    hasAttachments="Yes";
                    if(pre=='.')
                    {
                        attachmentName=previous;
                    }

                }
                else if(att.containsKey(operate))
                {
                    hasAttachments="Yes";
                }
                previous=operate;
                pre=p;
                operate="";
            }


        }

        if(str.indexOf(" from ")!=-1||str.indexOf(" sent by ")!=-1||str.indexOf(" by ")!=-1)
        {int ind=0;
            if(str.indexOf(" sent by ")!=-1)
            {
                ind= 6+ str.indexOf(" sent by ");
            }
            else if(str.indexOf(" from ")!=-1)
            {
                ind=  str.indexOf(" from ");
            }
            else
            {
                ind=  str.indexOf(" by ");
            }

            int start=str.indexOf(" ", ind+1);
            String next="";
            if(start!=-1)
            {
                int last= str.indexOf(" ", start+1);
                if(last==-1)
                {
                    next=str.substring(start+1);
                }
                else
                {
                    next=str.substring(start+1, last);
                }

                if(  !( next.compareTo("the")==0||next.compareTo("last")==0)   )
                {
                    from=next;
                }


            }
        }
        else if(str.indexOf("'s")!=-1)
        {
            int ind=str.indexOf("'s");

            int start=str.lastIndexOf(" ", ind );

            String temp=str.substring(start+1, ind);
            if(!present.containsKey(temp))
            {
                if ( ! (temp.compareTo("today")==0||temp.compareTo("yesterday")==0||
                        temp.compareTo("week")==0||temp.compareTo("month")==0||temp.compareTo("year")==0||temp.compareTo("day")==0)  )
                {
                    from=temp;
                }
            }

        }//else if
        else if(str.indexOf(" sent ")!=-1)
        {
            int ind=str.indexOf(" sent ");

            int start=str.lastIndexOf(" ", ind-1 );

            String  temp=str.substring(start+1, ind);

            if ( ! (temp.compareTo("is")==0||temp.compareTo("are")==0||temp.compareTo("sent")==0||
                    temp.compareTo("was")==0||temp.compareTo("were")==0||temp.compareTo("have")==0||
                    temp.compareTo("been")==0 ||temp.compareTo("has")==0||temp.compareTo("had")==0||
                    present.containsKey(temp)||temp.trim().length()==0 ||temp.compareTo("mails")==0 )  )
            {
                from=temp;
            }
        }//else if
        else if(str.indexOf(" mails ")!=-1)
        {
            int ind=str.indexOf(" mails ");

            int start=str.lastIndexOf(" ", ind-1 );

            String  temp=str.substring(start+1, ind);
            if ( ! (temp.compareTo("leave")==0||temp.compareTo("leaves")==0||temp.compareTo("all")==0||
                    temp.trim().length()==0||dateh.containsKey(temp)   ))
            {
                from=temp;
            }

        }
        if(str.indexOf(" cc to ")!=-1||str.indexOf(" cc'd to ")!=-1)
        {
            int ind =0;
            if(str.indexOf(" cc to ")!=-1)
            {
                ind= str.indexOf(" cc to ");
                ind =ind+4;
            }
            else
            {
                ind=str.indexOf(" cc'd to ");
                ind =ind+6;
            }
            int start=str.indexOf(" ", ind+1);
            if(start!=-1)
            {
                int last= str.indexOf(" ", start+1);

                if(last==-1)
                {
                    cc=str.substring(start+1);
                }
                else
                {
                    cc=str.substring(start+1, last);
                }

            }
        }//end of if
        else if(str.indexOf(" cc ")!=-1)
        {
            int start=str.indexOf(" ", str.indexOf(" cc ")+1);
            if(start!=-1)
            {
                int last= str.indexOf(" ", start+1);
                if(last==-1)
                {
                    cc=str.substring(start+1);
                }
                else
                {
                    cc=str.substring(start+1, last);
                }



            }
        }
        else if(str.indexOf(" was cc'd ")!=-1)
        {
            int ind=str.indexOf(" was cc'd ");
            int start=str.lastIndexOf(" ", ind-1);
            if(start!=-1)
            {
                cc=str.substring(start+1, ind);
            }
        }



        if(str.indexOf(" received ")!=-1||str.indexOf(" got ")!=-1)
        {
            int ind =0;
            if(str.indexOf(" received ")!=-1)
            {
                ind=str.indexOf(" received ");
            }
            else
            {
                ind=str.indexOf(" got ");
            }


            int start=str.lastIndexOf(" ", ind-1 );

            String  temp=str.substring(start+1, ind);
            //   System.out.println(" temp   "+temp);
            if ( ! (temp.compareTo("received" )==0||temp.compareTo("mails")==0||temp.trim().length()==0))
            {
                to=temp;
            }


        }//eif
        if(str.indexOf(" to ")!=-1)
        {
            int ind=str.indexOf(" to ");
            if( !( str.substring(ind-2,ind).compareTo("cc")==0||
                    str.substring(ind-2,ind).compareTo("'d")==0)   )
            {


                int start=str.indexOf(" ", str.indexOf(" to ")+1);
                if(start!=-1)
                {
                    int last= str.indexOf(" ", start+1);

                    if(last==-1)
                    {
                        to=str.substring(start+1);
                    }
                    else
                    {
                        to=str.substring(start+1, last);
                    }

                }
            }//inner if
        } // else if
        if(str.indexOf(" on ")!=-1)
        {

            int start=str.indexOf(" ", str.indexOf(" on ")+1);
            String date="";
            String mon="";
            String year="";
            if(start!=-1)
            {
                int last= str.indexOf(" ", start+1);
                if(last==-1)
                {
                    date=str.substring(start+1);
                }
                else
                {
                    date=str.substring(start+1, last);
                }


                int start1=str.indexOf(" ", str.indexOf(" "+date+" ")+1);

                if(start1!=-1)
                {
                    int last1= str.indexOf(" ", start1+1);
                    if(last1==-1)
                    {
                        mon=str.substring(start1+1);
                    }
                    else
                    {
                        mon=str.substring(start1+1, last1);
                    }

                    int start11=str.indexOf(" ", str.indexOf(" "+mon+" ")+1);

                    if(start11!=-1)
                    {
                        int last11= str.indexOf(" ", start11+1);
                        if(last11==-1)
                        {
                            year=str.substring(start11+1);
                        }
                        else
                        {
                            year=str.substring(start11+1, last11);
                        }


                    }// if
                }// if
            }
            if("january".contains(date)||"february".contains(date)||"march".contains(date)||
                    "april".contains(date)||"may".contains(date)||"june".contains(date)||
                    "july".contains(date)||"august".contains(date)||"september".contains(date)||
                    "october".contains(date)||"november".contains(date)||"december".contains(date))
            {
                fromdate=date;
                todate=date;
            }
            else if("january".contains(mon)||"february".contains(mon)||"march".contains(mon)||
                    "april".contains(mon)||"may".contains(mon)||"june".contains(mon)||
                    "july".contains(mon)||"august".contains(mon)||"september".contains(mon)||
                    "october".contains(mon)||"november".contains(mon)||"december".contains(mon))
            {
                fromdate=date+" "+mon;
                todate=date+" " +mon;
            }

            if(isInteger(year))
            {
                if(Integer.parseInt(year)>=1500&&Integer.parseInt(year)<=2050)
                {
                    fromdate=fromdate+" "+year;
                    todate=todate+" "+year;
                }
            }
        }//if
        if(str.indexOf(" last ")!=-1||str.indexOf(" past ")!=-1)
        {


            int ind =0;
            if(str.indexOf(" last ")!=-1)
            {
                ind= str.indexOf(" last ");

            }
            else
            {
                ind=str.indexOf(" past ");

            }
            int start=str.indexOf(" ", ind+1);




            if(start!=-1)
            {
                int last= str.indexOf(" ", start+1);
                String next="";
                if(last==-1)
                {
                    next=str.substring(start+1);
                }
                else
                {
                    next=str.substring(start+1, last);
                }

                if(next.compareTo("week")==0||next.compareTo("weeks")==0||next.compareTo("week's")==0)
                {
                    fromdate="Today";

                    todate="Today-7";
                }
                else if(next.compareTo("month")==0||next.compareTo("months")==0||next.compareTo("month's")==0)
                {
                    fromdate="Today";
                    todate="Today-30";
                }
                else if(next.compareTo("year")==0||next.compareTo("years")==0||next.compareTo("year's")==0)
                {
                    fromdate="Today";
                    todate="Today-365";
                }
                else if(next.compareTo("day")==0||next.compareTo("days")==0||next.compareTo("day's")==0)
                {
                    fromdate="Today";
                    todate="Today-1";
                }
                else
                {
                    int start1=str.indexOf(" ", str.indexOf(next));

                    int last1= str.indexOf(" ", start1+1);
                    String next1;
                    if(last1==-1)
                    {
                        next1=str.substring(start1+1);
                    }
                    else
                    {
                        next1=str.substring(start1+1, last1);
                    }
                    if(next1.compareTo("week")==0)
                    {
                        fromdate="Today";

                        todate="Today-"+7*Integer.parseInt(next);
                    }
                    else if(next1.compareTo("month")==0||next1.compareTo("months")==0)
                    {
                        fromdate="Today";
                        todate="Today-"+30*Integer.parseInt(next);
                    }
                    else if(next1.compareTo("year")==0||next1.compareTo("years")==0)
                    {
                        fromdate="Today";
                        todate="Today-"+365*Integer.parseInt(next);
                    }
                    else if(next1.compareTo("day")==0||next1.compareTo("days")==0)
                    {
                        fromdate="Today";
                        todate="Today-"+next;
                    }
                }//else
            }
        } ///else if
        if(str.indexOf("subject:")!=-1)
        {
            int start =str.indexOf("subject:")+"subject:".length();
            subject=str.substring(start, str.indexOf(" ",start));
        }

        if(str.indexOf("mb ")!=-1||str.indexOf("kb ")!=-1||str.indexOf("gb ")!=-1||
                str.indexOf("tb ")!=-1||str.indexOf(" mega bytes ")!=-1||
                str.indexOf(" kilo bytes ")!=-1||str.indexOf(" giga bytes ")!=-1||str.indexOf(" tera bytes ")!=-1)
        {
            int start=0;
            if(str.indexOf("mb ")!=-1)
            {
                start=str.lastIndexOf(" ",str.indexOf("mb "));
                int last=str.indexOf(" ",start+1);
                hasAttachments = "Yes";
                attachmentSize=str.substring(start+1, last);
            }
            else if(str.indexOf("kb ")!=-1)
            {
                start=str.lastIndexOf(" ",str.indexOf("kb "));
                int last=str.indexOf(" ",start+1);
                hasAttachments = "Yes";
                attachmentSize=str.substring(start+1, last);
            }
            else if(str.indexOf("gb ")!=-1)
            {
                start=str.lastIndexOf(" ",str.indexOf("gb "));
                int last=str.indexOf(" ",start+1);
                hasAttachments = "Yes";
                attachmentSize=str.substring(start+1, last);
            }
            else if(str.indexOf("tb ")!=-1)
            {
                start=str.lastIndexOf(" ",str.indexOf("tb "));
                int last=str.indexOf(" ",start+1);
                hasAttachments = "Yes";
                attachmentSize=str.substring(start+1, last);
            }
            else if(str.indexOf(" mega bytes ")!=-1)
            {
                start=str.lastIndexOf(" ",str.indexOf(" mega bytes ")-1);
                int last=str.indexOf(" ",start+1);
                hasAttachments = "Yes";
                attachmentSize=str.substring(start+1, last)+" mega bytes ";
            }
            else if(str.indexOf(" kilo bytes ")!=-1)
            {
                start=str.lastIndexOf(" ",str.indexOf(" kilo bytes ")-1);
                int last=str.indexOf(" ",start+1);
                hasAttachments = "Yes";
                attachmentSize=str.substring(start+1, last)+" kilo bytes ";
            }
            else if(str.indexOf(" giga bytes ")!=-1)
            {
                start=str.lastIndexOf(" ",str.indexOf(" giga bytes ")-1);
                int last=str.indexOf(" ",start+1);
                hasAttachments = "Yes";
                attachmentSize=str.substring(start+1, last)+" giga bytes ";
            }
            else if(str.indexOf(" tera bytes ")!=-1)
            {
                start=str.lastIndexOf(" ",str.indexOf(" tera bytes ")-1);
                int last=str.indexOf(" ",start+1);
                hasAttachments = "Yes";
                attachmentSize=str.substring(start+1, last)+" tera bytes ";
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("From ").append((char)(from.charAt(0)-32)).append(from.substring(1)).append("\n");
        sb.append("To ").append((char)(to.charAt(0)-32)).append(to.substring(1)).append("\n");
        sb.append("ToDate ").append(todate).append("\n");
        sb.append("FromDate ").append(fromdate).append("\n");
        sb.append("HasAttachments ").append(hasAttachments).append("\n");
        sb.append("AttachmentType ").append(attachmentType).append("\n");
        sb.append("AttachmentSize ").append(attachmentSize).append("\n");
        sb.append("AttachmentName ").append(attachmentName).append("\n");
        sb.append("Subject ").append((char)(subject.charAt(0)-32)).append(subject.substring(1)).append("\n");
        sb.append("CC ").append((char)(cc.charAt(0)-32)).append(cc.substring(1)).append("\n");
        return sb;
    }
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}