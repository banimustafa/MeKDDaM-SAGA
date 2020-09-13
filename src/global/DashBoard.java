/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package global;
import gui.form.input.DataForm;
import gui.form.input.StudyForm;
import gui.form.phase.PhaseForm;
import gui.form.supplement.HumanInteractionForm;
import gui.form.supplement.ManagementForm;
import gui.form.supplement.QualityForm;
import gui.form.supplement.StandardsForm;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import process_model.phase.Phase;
import process_model.phase.delivery.Delivery;

import process_model.phase.Phases;
import process_model.process.Process;
import process_model.supplement.human_interaction.HumanInteraction;
import process_model.supplement.management.Management;
import process_model.supplement.quality.Quality;
import process_model.supplement.standard.Standards;
import toolbox.Tools;

/**
 *
 * @author amb04
 */
public class DashBoard {

private static Phase selectPhase(Phase phase) throws IOException, Exception
{
    //Get the current process from the Global project
    Process process=new Process();

    process=Global.project.getProcess().clone();
//-------------------------------------------------------------------------
//-------------------------------------------------------------------------
if (process!=null)
   {
        // Get the current list of phases from the Global process
       Phases phases=(Phases) process.getPhases().clone();

//-------------------------------------------------------------------------
  if (phase!=null)
       {
         // prepare the passed selection name and add it to the phases
         switch (phase.getNumber())
            {
              case 1:
                phase.setName("Objectives Definition");
                phases.setObjectivesDefinition(phase.clone(true));
                break;
              case 2:
                phase.setName("Data Pre-Processing");
                phases.setDataPreProcessing(phase.clone(true));
                break;
              case 3:
                phase.setName("Data Exploration");
                phases.setDataExploration(phase.clone(true));
                break;
              case 4:
                  phase.setName("Technique Selection");
                  phases.setTechniqueSelection(phase.clone(true));
                  break;
              case 5:
                  phase.setName("Data Acclimatisation");
                  phases.setDataAcclimatisation(phase.clone(true));
                  break;
              case 6:
                  phase.setName("Model Building");
                  phases.setModelBuilding(phase.clone(true));
                   break;
              case 7:
                  phase.setName("Model Evaluation");
                  phases.setModelEvaluation(phase.clone(true));
                   break;
              case 8:
                  phase.setName("Knowledge Presentation");
                phases.setKnowledgePresentation(phase.clone(true));
                break;
              case 9:
                  phase.setName("Knowledge Evaluation");
                  phases.setKnowledgeEvaluation(phase.clone(true));
                break;
              case 10:
                  phase.setName("Deployment");
                  phases.setDeployment(phase.clone(true));
                break;
              case 11:
                  phase.setName("Process Evaluation");
                  phases.setProcessEvaluation(phase.clone(true));
                break;
              default:
                  JOptionPane.showMessageDialog(null, "No valid Phase selected!");
           }
//        System.out.println("Selected Phase: "+phase.toString());
    }
    //---------------------------------------------------------------------------
    if (phase!=null)
      {
        if (phase!=null && phase.save())
        {
             // calling the selection form
             PhaseForm phaseForm=new PhaseForm();
             phaseForm.setVisible(true);
             return phase.clone(true);
         }
        else
        {
            JOptionPane.showMessageDialog(null, "Phase saving has failed!");
            return null;
         }
       }
    else
     {
        JOptionPane.showMessageDialog(null, "No phase is available!");
        return null;
      }
  }
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
  else
     {
        JOptionPane.showMessageDialog(null, "No Process was found!");
        return null;
     }
}

//****************************************************************************
//****************************************************************************
//****************************************************************************
public static Object getXYAction( int x, int y) throws IOException, Exception
{
       Object selection=null;
       if (Global.project!=null)
       {
        if (Global.project.getProcess()==null)
               Global.project.setProcess(new Process());

         if ( Global.project.getProcess().getPhases()==null)
               Global.project.getProcess().setPhases(new Phases());
       }
       else
           JOptionPane.showMessageDialog(null, "No Project was found!");

        // inputs
        //----------------------------------------------------------------------        
        //----------------------------------------------------------------------

        if ( (x>207) && (x<266) && (y>163) && (y<202))
        {
         DataForm dataForm=new DataForm();
         dataForm.setVisible(true);
        }
        else if ( (x>275) && (x<334) && (y>164) && (y<204))
        {        
            StudyForm studyForm=new StudyForm();        
            studyForm.setVisible(true);

        }

        // Phases
        //----------------------------------------------------------------------
        //----------------------------------------------------------------------
        //----------------------------------------------------------------------

       else if ( (x>161) && (x<238) && (y>201) && (y<278))
           // if the pashe already exists in the process get it from phases  and select it
           if (Global.project!=null && Global.project.getProcess()!=null && Global.project.getProcess().getPhases()!=null && Global.project.getProcess().getPhases().get(1)!=null)
                selection=DashBoard.selectPhase(Global.project.getProcess().getPhases().get(1));
           // if it is not yet in the proocess check hisory for the latest version and use it
           else if (Global.project.getProcess().getIteration()!=null && Global.project.getProcess().getIteration().size()>0)
            {
                int size=Global.project.getProcess().getIteration().size();
                Process latestProcess=(Process) Global.project.getProcess().getIteration().get(size-1);
                if (latestProcess.getPhases()!=null&&latestProcess.getPhases().get().size()>0)
                    {
                        Phase latestPhase=null;

                       if (latestProcess.getPhases().get(1)!=null)
                            latestPhase= latestProcess.getPhases().get(1).clone(true);
                       else
                           latestPhase= new Phase(1);

                        latestPhase.iterate();
                        selection = DashBoard.selectPhase(latestPhase);
                    }
            }
            else
                selection = DashBoard.selectPhase(new Phase(1));

       //---------------------------------------------------------------------
        else if ( (x>138) && (x<215) && (y>300) && (y<377))
        {
           if (Global.project!=null && Global.project.getProcess()!=null && Global.project.getProcess().getPhases()!=null && Global.project.getProcess().getPhases().get(2)!=null)
                selection=DashBoard.selectPhase(Global.project.getProcess().getPhases().get(2));
            else if (Global.project.getProcess().getPhases().get(1)!=null) // previous version exists

            {  if (Global.project.getProcess().getIteration()!=null && Global.project.getProcess().getIteration().size()>0)
                {
                    int size=Global.project.getProcess().getIteration().size();
                    Process latestProcess=(Process) Global.project.getProcess().getIteration().get(size-1);
                    if (latestProcess.getPhases()!=null&&latestProcess.getPhases().get().size()>0)
                    {
                        Phase latestPhase=null;

                       if (latestProcess.getPhases().get(2)!=null)
                            latestPhase= latestProcess.getPhases().get(2).clone(true);
                       else
                           latestPhase= new Phase(2);
                        latestPhase.iterate();
                        selection = DashBoard.selectPhase(latestPhase);
                    }
                }
                else
                    selection = DashBoard.selectPhase(new Phase(2));
            }
            else
               JOptionPane.showMessageDialog(null, "Need to maintain the process flow...Previous phase has'nt been executed yet!");
        }

       //---------------------------------------------------------------------
        else if ( (x>137) && (x<216) && (y>404) && (y<481))
             {
            if (Global.project!=null && Global.project.getProcess()!=null && Global.project.getProcess().getPhases()!=null && Global.project.getProcess().getPhases().get(3)!=null)
                selection=DashBoard.selectPhase(Global.project.getProcess().getPhases().get(3));
            else if (Global.project.getProcess().getPhases().get(2)!=null) // previous version exists

            {  if (Global.project.getProcess().getIteration()!=null&& Global.project.getProcess().getIteration().size()>0)
                {
                    int size=Global.project.getProcess().getIteration().size();
                    Process latestProcess=(Process) Global.project.getProcess().getIteration().get(size-1);
                    if (latestProcess.getPhases()!=null&&latestProcess.getPhases().get().size()>0)
                    {
                        Phase latestPhase=null;

                       if (latestProcess.getPhases().get(3)!=null)
                            latestPhase= latestProcess.getPhases().get(3).clone(true);
                       else
                           latestPhase= new Phase(3);
                        latestPhase.iterate();
                        selection = DashBoard.selectPhase(latestPhase);
                    }
                }
                else
                    selection = DashBoard.selectPhase(new Phase(3));
            }
            else
               JOptionPane.showMessageDialog(null, "Need to maintain the process flow...Previous phase has'nt been executed yet!");
        }


        //---------------------------------------------------------------------
        else if ( (x>188) && (x<268) && (y>491) && (y<570))
              {
            if (Global.project!=null && Global.project.getProcess()!=null && Global.project.getProcess().getPhases()!=null && Global.project.getProcess().getPhases().get(4)!=null)
                selection=DashBoard.selectPhase(Global.project.getProcess().getPhases().get(4));
            else if (Global.project.getProcess().getPhases().get(3)!=null) // previous version exists

            {  if (Global.project.getProcess().getIteration()!=null&& Global.project.getProcess().getIteration().size()>0)
                {
                    int size=Global.project.getProcess().getIteration().size();
                    Process latestProcess=(Process) Global.project.getProcess().getIteration().get(size-1);
                    if (latestProcess.getPhases()!=null&&latestProcess.getPhases().get().size()>0)
                    {
                        Phase latestPhase=null;

                       if (latestProcess.getPhases().get(4)!=null)
                            latestPhase= latestProcess.getPhases().get(4).clone(true);
                       else
                           latestPhase= new Phase(4);
                        latestPhase.iterate();
                        selection = DashBoard.selectPhase(latestPhase);
                    }
               }
                else
                    selection = DashBoard.selectPhase(new Phase(4));
            }
            else
               JOptionPane.showMessageDialog(null, "Need to maintain the process flow...Previous phase has'nt been executed yet!");
        }

       //---------------------------------------------------------------------
        else if ( (x>280) && (x<358) && (y>554) && (y<633))
          {
            if (Global.project!=null && Global.project.getProcess()!=null && Global.project.getProcess().getPhases()!=null && Global.project.getProcess().getPhases().get(5)!=null)
                selection=DashBoard.selectPhase(Global.project.getProcess().getPhases().get(5));
            else if (Global.project.getProcess().getPhases().get(4)!=null) // previous version exists

            {  if (Global.project.getProcess().getIteration()!=null&& Global.project.getProcess().getIteration().size()>0)
                {
                    int size=Global.project.getProcess().getIteration().size();
                    Process latestProcess=(Process) Global.project.getProcess().getIteration().get(size-1);
                    if (latestProcess.getPhases()!=null&&latestProcess.getPhases().get().size()>0)
                    {
                        Phase latestPhase=null;
                       
                       if (latestProcess.getPhases().get(5)!=null)                       
                            latestPhase= latestProcess.getPhases().get(5).clone(true);
                       else
                           latestPhase= new Phase(5);

                        latestPhase.iterate();
                        selection = DashBoard.selectPhase(latestPhase);
                    }
                }
                else
                    selection = DashBoard.selectPhase(new Phase(5));
            }
            else
               JOptionPane.showMessageDialog(null, "Need to maintain the process flow...Previous phase has'nt been executed yet!");
          }

        //---------------------------------------------------------------------
        else if ( (x>397) && (x<476) && (y>554) && (y<632))
          {
            if (Global.project!=null && Global.project.getProcess()!=null && Global.project.getProcess().getPhases()!=null && Global.project.getProcess().getPhases().get(6)!=null)
                selection=DashBoard.selectPhase(Global.project.getProcess().getPhases().get(6));
            else if (Global.project.getProcess().getPhases().get(5)!=null) // previous version exists

            {  if (Global.project.getProcess().getIteration()!=null&& Global.project.getProcess().getIteration().size()>0)
                {
                    int size=Global.project.getProcess().getIteration().size();
                    Process latestProcess=(Process) Global.project.getProcess().getIteration().get(size-1);

                    if (latestProcess.getPhases()!=null&&latestProcess.getPhases().get().size()>0)
                    {
                        //Phase latestPhase=latestProcess.getPhases().get(6).clone(true);
                       Phase latestPhase=null;
                       
                       if (latestProcess.getPhases().get(6)!=null)
                            latestPhase= latestProcess.getPhases().get(6).clone(true);
                       else
                           latestPhase= new Phase(6);

                        latestPhase.iterate();
                        selection = DashBoard.selectPhase(latestPhase);

                    }
                }
                else
                    selection = DashBoard.selectPhase(new Phase(6));
            }
            else
               JOptionPane.showMessageDialog(null, "Need to maintain the process flow...Previous phase has'nt been executed yet!");
          }

        //---------------------------------------------------------------------
        else if ( (x>487) && (x<567) && (y>493) && (y<571))
          {
            if (Global.project!=null && Global.project.getProcess()!=null && Global.project.getProcess().getPhases()!=null && Global.project.getProcess().getPhases().get(7)!=null)
                selection=DashBoard.selectPhase(Global.project.getProcess().getPhases().get(7));
            else if (Global.project.getProcess().getPhases().get(6)!=null) // previous version exists

            {  if (Global.project.getProcess().getIteration()!=null&& Global.project.getProcess().getIteration().size()>0)
                {
                    int size=Global.project.getProcess().getIteration().size();
                    Process latestProcess=(Process) Global.project.getProcess().getIteration().get(size-1);
                    if (latestProcess.getPhases()!=null&&latestProcess.getPhases().get().size()>0)
                    {
                        Phase latestPhase=null;

                       if (latestProcess.getPhases().get(7)!=null)
                            latestPhase= latestProcess.getPhases().get(7).clone(true);
                       else
                           latestPhase= new Phase(7);
                        latestPhase.iterate();
                        selection = DashBoard.selectPhase(latestPhase);
                    }
                }
                else
                    selection = DashBoard.selectPhase(new Phase(7));
            }
            else
               JOptionPane.showMessageDialog(null, "Need to maintain the process flow...Previous phase has'nt been executed yet!");
          }

        //---------------------------------------------------------------------        
        else if ( (x>541) && (x<618) && (y>403) && (y<482))
          {
            if (Global.project!=null && Global.project.getProcess()!=null && Global.project.getProcess().getPhases()!=null && Global.project.getProcess().getPhases().get(8)!=null)
                selection=DashBoard.selectPhase(Global.project.getProcess().getPhases().get(8));
            else if (Global.project.getProcess().getPhases().get(7)!=null) // previous version exists

            {  if (Global.project.getProcess().getIteration()!=null&& Global.project.getProcess().getIteration().size()>0)
                {
                    int size=Global.project.getProcess().getIteration().size();
                    Process latestProcess=(Process) Global.project.getProcess().getIteration().get(size-1);
                    if (latestProcess.getPhases()!=null&&latestProcess.getPhases().get().size()>0)
                    {
                        Phase latestPhase=null;

                       if (latestProcess.getPhases().get(8)!=null)
                            latestPhase= latestProcess.getPhases().get(8).clone(true);
                       else
                           latestPhase= new Phase(8);
                        latestPhase.iterate();
                        selection = DashBoard.selectPhase(latestPhase);
                    }
                }
                else
                    selection = DashBoard.selectPhase(new Phase(8));
            }
            else
               JOptionPane.showMessageDialog(null, "Need to maintain the process flow...Previous phase has'nt been executed yet!");
          }

        //---------------------------------------------------------------------        
        else if ( (x>541) && (x<619) && (y>300) && (y<377))
          {
            if (Global.project!=null && Global.project.getProcess()!=null && Global.project.getProcess().getPhases()!=null && Global.project.getProcess().getPhases().get(9)!=null)
                selection=DashBoard.selectPhase(Global.project.getProcess().getPhases().get(9));
            else if (Global.project.getProcess().getPhases().get(8)!=null) // previous version exists

            {  if (Global.project.getProcess().getIteration()!=null&& Global.project.getProcess().getIteration().size()>0)
                {
                    int size=Global.project.getProcess().getIteration().size();
                    Process latestProcess=(Process) Global.project.getProcess().getIteration().get(size-1);
                    if (latestProcess.getPhases()!=null&&latestProcess.getPhases().get().size()>0)
                    {
                        Phase latestPhase=null;

                       if (latestProcess.getPhases().get(9)!=null)
                            latestPhase= latestProcess.getPhases().get(9).clone(true);
                       else
                           latestPhase= new Phase(9);
                        latestPhase.iterate();
                        selection = DashBoard.selectPhase(latestPhase);
                    }
                }
                else
                    selection = DashBoard.selectPhase(new Phase(9));
            }
            else
               JOptionPane.showMessageDialog(null, "Need to maintain the process flow...Previous phase has'nt been executed yet!");
          }

        //---------------------------------------------------------------------        
        else if ( (x>517) && (x<595) && (y>200) && (y<280))
          {
        if (Global.project!=null && Global.project.getProcess()!=null && Global.project.getProcess().getPhases()!=null && Global.project.getProcess().getPhases().get(10)!=null)
                selection=DashBoard.selectPhase(Global.project.getProcess().getPhases().get(10));
            else if (Global.project.getProcess().getPhases().get(9)!=null) // previous version exists

            {  if (Global.project.getProcess().getIteration()!=null && Global.project.getProcess().getIteration().size()>0)
                {
                    int size=Global.project.getProcess().getIteration().size();
                    Process latestProcess=(Process) Global.project.getProcess().getIteration().get(size-1);
                    if (latestProcess.getPhases()!=null&&latestProcess.getPhases().get().size()>0)
                    {
                        Phase latestPhase=null;

                       if (latestProcess.getPhases().get(10)!=null)
                            latestPhase= latestProcess.getPhases().get(10).clone(true);
                       else
                           latestPhase= new Phase(10);
                        latestPhase.iterate();                        
                        selection = DashBoard.selectPhase(latestPhase);
                    }
                }
                else
                    selection = DashBoard.selectPhase(new Phase(10));
            }
            else
               JOptionPane.showMessageDialog(null, "Need to maintain the process flow...Previous phase has'nt been executed yet!");
          }

        //---------------------------------------------------------------------
        else if ( (x>338) && (x<417) && (y>125) && (y<203))
          {
            if (Global.project!=null && Global.project.getProcess()!=null && Global.project.getProcess().getPhases()!=null && Global.project.getProcess().getPhases().get(11)!=null)
                selection=DashBoard.selectPhase(Global.project.getProcess().getPhases().get(11));
            else if (Global.project.getProcess().getPhases().get(10)!=null) // previous version exists

            {  if (Global.project.getProcess().getIteration()!=null&& Global.project.getProcess().getIteration().size()>0)
                {
                    int size=Global.project.getProcess().getIteration().size();
                    Process latestProcess=(Process) Global.project.getProcess().getIteration().get(size-1);
                    if (latestProcess.getPhases()!=null&&latestProcess.getPhases().get().size()>0)
                    {
                        Phase latestPhase=null;

                       if (latestProcess.getPhases().get(11)!=null)
                            latestPhase= latestProcess.getPhases().get(11).clone(true);
                       else
                           latestPhase= new Phase(11);
                        
                        latestPhase.iterate();
                        selection = DashBoard.selectPhase(latestPhase);
                    }
                }
                else
                    selection = DashBoard.selectPhase(new Phase(11));
            }
            else
               JOptionPane.showMessageDialog(null, "Need to maintain the process flow...Previous phase has'nt been executed yet!");
          }

        //----------------------------------------------------------------------
        //----------------------------------------------------------------------
        //                              Suplements
        //----------------------------------------------------------------------
        //----------------------------------------------------------------------
        else if ( (x>41) && (x<91) && (y>31) && (y<83))
        {          
            selection=new Quality();
            QualityForm qualityForm=new QualityForm();
            qualityForm.setTitle(selection.getClass().getSimpleName());
            qualityForm.setVisible(true);
       
       
        }
        else if ( (x>16) && (x<120) && (y>582) && (y<684))
        {
            selection=new Standards();
            StandardsForm standardsForm=new StandardsForm(null);
            standardsForm.setTitle(selection.getClass().getSimpleName());
            standardsForm.setVisible(true);
        }
        
       else if ( (x>625) && (x<713) && (y>16) && (y<107))
        {
            selection=new Management();
            ManagementForm supplementForm=new ManagementForm();
            supplementForm.setTitle(selection.getClass().getSimpleName());
            supplementForm.setVisible(true);
        }
      
       else if ( (x>646) && (x<723) && (y>609) && (y<686))
        {
            selection=new HumanInteraction();
            HumanInteractionForm humanInteractionForm=new HumanInteractionForm();
            humanInteractionForm.setVisible(true);
        }

       else if ( (x>750) && (x<865) && (y>203) && (y<411))
        {
            selection=new HumanInteraction();
            HumanInteractionForm humanInteractionForm=new HumanInteractionForm();
            humanInteractionForm.setTitle(selection.getClass().getSimpleName());
            humanInteractionForm.setVisible(true);
        }
       
       else if ( (x>750) && (x<850) && (y>0) && (y<178))
        selection = DashBoard.selectPhase(Global.currentPhase);

        //----------------------------------------------------------------------
        //----------------------------------------------------------------------
        //                             deleveries
        //----------------------------------------------------------------------
        //----------------------------------------------------------------------
        else if ( (x>281) && (x<331) && (y>277) && (y<319))
         show_delivery(1);


        else if ( (x>222) && (x<284) && (y>347) && (y<388))
         show_delivery(2);


        else if ( (x>230) && (x<277) && (y>442) && (y<485))
         show_delivery(3);

        else if ( (x>290) && (x<341) && (y>493) && (y<538))
         show_delivery(4);

        else if ( (x>352) && (x<405) && (y>532) && (y<568))
         show_delivery(5);


        else if ( (x>414) && (x<465) && (y>494) && (y<536))
         show_delivery(6);


        else if ( (x>481) && (x<526) && (y>443) && (y<485))
         show_delivery(7);

        else if ( (x>479) && (x<529) && (y>342) && (y<388))
         show_delivery(8);


        else if ( (x>479) && (x<526) && (y>278) && (y<319))
         show_delivery(9);

        else if ( (x>328) && (x<430) && (y>215) && (y<264))
         show_delivery(10);

        else if ( (x>329) && (x<435) && (y>87) && (y<115))
         show_delivery(11);


       // No Action
       //----------------------------------------------------------------------
       //----------------------------------------------------------------------
       //----------------------------------------------------------------------
       else
        selection="No Valid Selection";
    return selection;                            
}

//****************************************************************************
    public static void show_delivery(int index)
    {
          //Global.currentPhase.store(null);

           Delivery delivery=null;
           String location=null;
           if (Global.project!=null &&
               Global.project.getProcess()!=null &&
               Global.project.getProcess().getResults()!=null &&
               Global.project.getProcess().getResults().getResult(index)!=null &&
               Global.project.getProcess().getResults().getResult(index).getMainDelivery()!=null)
          {

            delivery= (Delivery) Global.project.getProcess().getResults().getResult(index).getMainDelivery();
            if (delivery.getLocation()!=null)
                location=delivery.getLocation();
          }
           else if (Global.project.getProcess()!=null &&
                    Global.project.getProcess().getResults()!=null &&
                    Global.project.getProcess().getResults().getResult(index)!=null &&
                    Global.project.getProcess().getResults().getResult(index).getSupplementDeliveries()!=null &&
                    Global.project.getProcess().getResults().getResult(index).getSupplementDeliveries().getFirst()!=null &&
                    Global.project.getProcess().getResults().getResult(index).getSupplementDeliveries().getFirst().getLocation()!=null)
           {
               delivery=Global.project.getProcess().getResults().getResult(index).getSupplementDeliveries().getFirst();
               location=Global.project.getProcess().getResults().getResult(index).getSupplementDeliveries().getFirst().getLocation();
           }
            if (location !=null && delivery!=null)
            {
                String fileName=location+"/"+delivery.getClass().getSimpleName();
                if (new File(fileName+".xml").exists())
                    Tools.viewAnyFile(new File(fileName+".xml"));
                else
                {
                    delivery.deliverAsMain();
                    if (new File(fileName+".xml").exists())
                        Tools.viewAnyFile(new File(fileName+".xml"));
                    else                    
                        JOptionPane.showMessageDialog(null, "No delivery was Found!\n" +new File(fileName+".xml").getPath());
                 }
            }
           else
             JOptionPane.showMessageDialog(null, "No delivery has been generated yet!");

  }
}
//****************************************************************************
