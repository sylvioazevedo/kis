/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kis.controller

/**
 *
 * @author sazevedo
 */
class FileController {

    static boolean isKisFile(File file) {
        
        def name = file.name
        String ext = name.substring(name.lastIndexOf(".") + 1, name.length())

        ext.equalsIgnoreCase("kis")
    }

    public static String identifyFile(File file) {
        
        def ext = (getExtension(file) as String).toLowerCase()

        switch(ext) {
            
            case "kis":
                return "Keep It Secret file (kis)."
                
            case "txt":
                return "A simple plain text file."
              
            case "xml":
                return "A XML (Extensible Markup Language) file."
                
            case "doc":
            case "docx":
                return "A Microsoft Word document file.";
                
            case "xls":
            case "xlsx":
                return "A Microsoft Excel document file."
                
            case "pdf":
                return "A Adobe Portable Document Format file (pdf)."
                
            case "htm":
            case "html":
                return "A Hyper-text Markup Language file (html)."
                
            case "ppt":
            case "pptx":
                return "A Microsoft Presentation file."
                
            case "wmv":
                return "A Windows Media Player file (wmv)."
                
            case "mp3":
                return "A MPEG audio file (mp3)."
                
            default:
                 return "Unknown file type ${ext}."
        }
    }

    static String getExtension(File file) {
        String name = file.name;
        name.substring(name.lastIndexOf(".") + 1, name.length())
    }

    public static String getName(File file) { 
        file.name.substring(0, file.name.indexOf("."))
    }
}

