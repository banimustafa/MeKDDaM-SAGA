package project;
import java.io.File;
import java.io.IOException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlSeeAlso;
import global.Config;
import global.Global;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAttribute;
import process_model.issue.persistence.Persistable;
import process_model.process.Process;
import process_model.issue.persistence.Persistence;
import process_model.issue.tracibility.Sources;
import process_model.phase.Phase;
import process_model.process.input.Inputs;
import process_model.process.result.Results;
import process_model.supplement.Supplements;
import process_model.supplement.management.Management;
import toolbox.Tools;
import toolbox.datatools.XMLTools;
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
@XmlSeeAlso({Persistence.class,Management.class, Process.class, Inputs.class, Sources.class, Results.class})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Project",namespace = "", propOrder = {
    "name",
    "location",
    "date",
    "description",
    "persistence",
    "inputs",
    "sources",
    "process",    
    "supplements",
    "results"
})
@XmlRootElement
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
public class Project implements Persistable{
//-----------------------------------------------------------------------------   
@XmlAttribute
private String name=this.getClass().getSimpleName();
private String location=null;
private String date=Tools.getTime().toString();
private String description=null;
private Persistence persistence= null;
private Inputs inputs= null;
private Sources sources=null;
private Process process= null;
private Supplements supplements=null;
private Results results= null;

// CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC
public Project() throws IOException, Exception
{
/*
    this.location= Tools.chooseFile("Choose project location",
                                 null, Config.defaultLocation, true).getPath();
 */
    name=this.getClass().getSimpleName();
    date=Tools.getTime().toString();
}

//----------------------------------------------------------------------------
public Project(String pLocation)
{
            location = pLocation;
               name=this.getClass().getSimpleName();
    date=Tools.getTime().toString();
}

//----------------------------------------------------------------------------
public Project(String pName, String pLocation)
{
            name = pName;
            date = Tools.getTime().toString();
}
//----------------------------------------------------------------------------
public Project(String pName,String pLocation, String pDescription)
{
            name = pName;
            location = pLocation;
            date = Tools.getTime().toString();
            description=pDescription;

}    

//GSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGSGS
    public void setName(String pName) {
        this.name = pName;

    }

    public void setLocation(String plocation) {
        this.location = plocation;
    }

    public void setDescription(String pdescription) {
        this.description = pdescription;
    }
    public void setDate(String pDate) {
        this.date= pDate;
    }
    
    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }   
   public String getDate() {
        return date;
    }
   public String getDescription() {
        return description;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Process getProcess() {
        return process;
    }

// SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
    @Override
public String toString()
{
    String string=null;
    string="-------------------------------------------------------\n";
    string=string+this.getName()+"\n";
    string=string+this.getLocation()+"\n";
    string=string+this.getDate()+"\n";
    string=string+this.getDescription()+"\n";
    if (this.persistence!=null)
        string=string+this.persistence.toString()+"\n";
    if (this.inputs!=null)
        string=string+this.inputs.toString()+"\n";
    if (this.sources!=null)
        string=string+this.sources.toString()+"\n";
    if (this.process!=null)
        string=string+this.process.toString()+"\n";
    if (this.supplements!=null)
        string=string+this.supplements.toString()+"\n";
    if (this.results!=null)
        string=string+this.results.toString()+"\n";
    string=string+"-------------------------------------------------------";
    return string;
}

//IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
//IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
//IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
 @Override
public Project open()
{
    // Phase openining
    //------------------------------------------------------------------------
    try {
        Project project=new Project(null);
        project=(Project) XMLTools.loadObject(this,null);
        if (project!=null)
        {
            Global.project=project;
            project.store(null);
            return project;
        }
        else
        {
           //System.out.println("Cannot open Phase... Phase not found");
            return null;
        }
       } catch (Exception ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
}

 //IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
 //IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
 //IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
    @Override
public void persist(Persistence persistenceMechanism)
    {
        try {

            if (persistenceMechanism!=null)
                this.persistence = persistenceMechanism;
            else
                this.persistence=new Persistence();

            if (this.persistence.getUrl() == null) {
                this.persistence.setUrl(new URL("file:/"+
                Tools.chooseFile("Choose project location",
                                 null, Config.defaultLocation, true).getPath()));
            }
            try {
//                System.out.println("\nget path"+persistence.getUrl().getPath() .toString());
                XMLTools.storeObject(this, new File(this.persistence.getUrl().getPath().toString()),null);

            } catch (IOException ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 //IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
 //IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
 //IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
    @Override
    public Persistence getPersistance() {
        return this.persistence;
    }
 //IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
 //IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
 //IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
    @Override
    public void store(String location) {

       try{
         //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
         //xxxxxxxxxxxxxxxxxxxxxxxxxxx( Checking the location )xxxxxxxxxxxxxxx
         //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
         if (location==null||location.equals(""))
             if (Global.project.getLocation()==null)
             {
                String chosenLocation=Tools.chooseFile("Choose project location",
                             null, Config.defaultLocation, true).getPath();
                 if (chosenLocation!=null)
                     this.location=chosenLocation;
//                 else
//                    System.out.println("Oops...No location was selected...Project Failed to store");
             }
             else
                 this.location=Global.project.getLocation();
         else
            this.location=location;

         //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
         //xxxxxxxxxxxxxxxxxxxxxxxxxxxxx (Storing Project)xxxxxxxxxxxxxxxxxxx
         //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx         
         if (this.location!=null & this.name!=null)
         {
             File projectFile=new File(this.location+"/"+this.name).getAbsoluteFile();
             XMLTools.storeObject(this,projectFile,this.name);
         }
//         else
//            System.out.println("Oops...projcet location was found...Project Failed to store!");
    
         // storing the process
         //==================================================================
          if (this.process!=null)
             process.store(null);

          if (this.supplements!=null)
             supplements.store();

         if (this.sources!=null)
             sources.store(null);

         if (this.inputs!=null)
             inputs.store();

         if (!new File(this.location+"/"+this.name+"/"+"Temp").isDirectory())
             new File(this.location+"/"+this.name+"/"+"Temp").mkdirs();

         File outcomesReadme=new File(this.location+"/"+this.name+"/"+"Temp"+"/"+"ReadMe.txt");
         Tools.appendStringToFile("This is a directory allocated to store the outputs of external tools\n", outcomesReadme.getPath());

          // Make sure that the project is the active project
         //-------------------------------------------------------------------
         //-------------------------------------------------------------------
          Global.project=this;

        } catch (IOException ex) {
            Logger.getLogger(Phase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Phase.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }


    public void save()
    {
          Global.project=this;
    }
  
    public void setPersistence(Persistence persistence) {
        this.persistence = persistence;
    }

    public Persistence getPersistence() {
        return persistence;
    }

    public Supplements getSupplements() {
        return supplements;
    }

    public void setSupplements(Supplements supplements) {
        this.supplements = supplements;
    }

    public Sources getSources() {
        return sources;
    }

    public void setSources(Sources sources) {
        this.sources = sources;
    }

    public Inputs getInputs() {
        return inputs;
    }

    public void setInputs(Inputs inputs) {
        this.inputs = inputs;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

}
