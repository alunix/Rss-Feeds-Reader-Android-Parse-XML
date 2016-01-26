# Rss-Feeds-Reader-Android-Parse-XML

Parse this type of Rss Feeds XML

![Settings Window](https://github.com/aman007jham/Rss_Reader_Android_Parse_XML/blob/master/rss.png)


![alt-text-1](https://github.com/aman007jham/Rss_Reader_Android_Parse_XML/blob/master/1.png "VIEW-1") ![alt-text-2](https://github.com/aman007jham/Rss_Reader_Android_Parse_XML/blob/master/2.png "VIEW-2")


RSS Parser

       try {
            RSSXMLTag currentTag = null;
            ArrayList<Pojo> postDataList = new ArrayList<>();
            XmlPullParserFactory factory = XmlPullParserFactory
                    .newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(Data));
                           int eventType = xpp.getEventType();
                             Pojo pdData = null;
                      SimpleDateFormat dateFormat = new SimpleDateFormat(
                              "EEEE, DD MMM yyyy ");
                      while (eventType != XmlPullParser.END_DOCUMENT) {
                          int i = 0;
                          if (eventType == XmlPullParser.START_DOCUMENT) {
                } else if (eventType == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("item")) {
                        pdData = new Pojo();
                        currentTag = RSSXMLTag.IGNORETAG;
                    } else if (xpp.getName().equals("title")) {
                        currentTag = RSSXMLTag.TITLE;
                    } else if (xpp.getName().equals("link")) {
                        currentTag = RSSXMLTag.LINK;
                    } else if (xpp.getName().equals("pubDate")) {
                        currentTag = RSSXMLTag.DATE;
                    } else if (xpp.getName().equals("creator")) {
                        currentTag = RSSXMLTag.CREATOR;
                    } else if (xpp.getName().equals("category")) {
                        currentTag = RSSXMLTag.CATEGORY;
                    } else if (xpp.getName().equals("description")) {
                        currentTag = RSSXMLTag.DESCRIPTION;
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equals("item")) {
                        // format the data here, otherwise format data in
                        // Adapter
                        Date postDate = dateFormat.parse(pdData.postDate);
                        pdData.postDate = dateFormat.format(postDate);
                        postDataList.add(pdData);
                    } else {
                        currentTag = RSSXMLTag.IGNORETAG;
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    String content = xpp.getText();
                    content = content.trim();
                    Log.d("debug", content);
                    if (pdData != null) {
                        switch (currentTag) {
                            case TITLE:
                                if (content.length() != 0) {
                                    if (pdData.postTitle != null) {
                                        pdData.postTitle += content;
                                    } else {
                                        pdData.postTitle = content;
                                    }
                                }
                                break;
                            case LINK:
                                if (content.length() != 0) {
                                    if (pdData.postLink != null) {
                                        pdData.postLink += content;
                                    } else {
                                        pdData.postLink = content;
                                    }
                                }
                                break;
                            case DATE:
                                if (content.length() != 0) {
                                    if (pdData.postDate != null) {
                                        pdData.postDate += content;
                                    } else {
                                        pdData.postDate = content;
                                    }
                                }
                                break;
                            case CATEGORY:
                                if (content.length() != 0) {
                                    if (pdData.postCategory != null) {
                                        i = i + 1;
                                        if (i == 1) {
                                            pdData.postCategory = content;
                                        }
                                    } else {
                                        i = i + 1;
                                        if (i == 1) {
                                            pdData.postCategory = content;
                                        }
                                    }
                                }
                                break;
                            case DESCRIPTION:
                                if (content.length() != 0) {
                                    if (pdData.postDescription != null) {
                                        String s = content;
                                        String string = s.substring(s.indexOf("src=\"") + 5, s.indexOf("\" class=\""));
                                        pdData.postDescription += string;
                                    } else {
                                        String s = content;
                                        String string = s.substring(s.indexOf("src=\"") + 5, s.indexOf("\" class=\""));
                                        pdData.postDescription = string;
                                    }
                                }
                                break;
                            case CREATOR:
                                if (content.length() != 0) {
                                    if (pdData.postCreator != null) {
                                        pdData.postCreator += content;
                                    } else {
                                        pdData.postCreator = content;
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
                eventType = xpp.next();
            }
            return postDataList;
        }catch (Exception e){
            e.printStackTrace();
        }
        
        
        
