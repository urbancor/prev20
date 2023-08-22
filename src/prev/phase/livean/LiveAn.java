package prev.phase.livean;

import java.util.*;

import prev.data.mem.*;
import prev.data.asm.*;
import prev.phase.*;
import prev.phase.asmgen.*;

/**
 * Liveness analysis.
 */
public class LiveAn extends Phase {

	public LiveAn() {
		super("livean");
	}

	public void analysis() {
		

		for(Code code : AsmGen.codes()) {
			HashMap<String,AsmLABEL> labels = new HashMap<String,AsmLABEL>();
			for(AsmInstr tmp : code.instrs) {
				if(tmp instanceof AsmLABEL){
					AsmLABEL labela = (AsmLABEL) tmp;
					labels.put(labela.toString(), labela);
				}
			}
			boolean changed = true;
			while(changed) {
				changed = false;
				
				for(int i = 0; i < code.instrs.size(); i++) {
					AsmInstr instr = code.instrs.get(i);
					HashSet<MemTemp> old_in = new HashSet<MemTemp>(instr.in());
					HashSet<MemTemp> old_out = new HashSet<MemTemp>(instr.out());

					HashSet<MemTemp> in = new HashSet<MemTemp>();
					for(MemTemp mt : instr.uses()) {
						in.add(mt);
					}
					HashSet<MemTemp> difference = new HashSet<MemTemp>();
					for(MemTemp mt : instr.out()) {
						difference.add(mt);
					}
					//difference.removeAll(new HashSet<MemTemp>(instr.defs()));
					for(MemTemp mt : instr.defs()) {
						difference.remove(mt);
					}
					in.addAll(difference);
					instr.addInTemps(in);
					HashSet<MemTemp> out = new HashSet<MemTemp>();

					if(!(instr.jumps().isEmpty())) {
						for(MemLabel memLabel : instr.jumps()) {
							//System.out.println(code.frame.label.name);
							//if(memLabel != code.exitLabel && memLabel != code.frame.label/*|| memLabel != code.entryLabel*/) {
								/*System.out.println(memLabel.name);
								System.out.println(labels.get(memLabel.name));*/
							if(labels.get(memLabel.name) != null) {			// if this is not true that means that it is the function label
								out.addAll(labels.get(memLabel.name).in());
							} else if(memLabel == code.exitLabel) {
								out.add(code.frame.RV);
							}
						}
					} //else {
					if(i+1<code.instrs.size()){
						AsmInstr next = code.instrs.get(i+1);
						out.addAll(next.in());
					}
					//}
					instr.addOutTemp(out);
					if(old_in.size() != instr.in().size()) {
						changed = true;
					}
					if(old_out.size() != instr.out().size()) {
						changed = true;
					}
					/*if(!(old_in.equals(in) && old_out.equals(out))) {
						changed = true;	
					} */
				} 
			}
		}
	}
	
	public void log() {
		if (logger == null)
			return;
		for (Code code : AsmGen.codes) {
			logger.begElement("code");
			logger.addAttribute("entrylabel", code.entryLabel.name);
			logger.addAttribute("exitlabel", code.exitLabel.name);
			logger.addAttribute("tempsize", Long.toString(code.tempSize));
			code.frame.log(logger);
			logger.begElement("instructions");
			for (AsmInstr instr : code.instrs) {
				logger.begElement("instruction");
				logger.addAttribute("code", instr.toString());
				logger.begElement("temps");
				logger.addAttribute("name", "use");
				for (MemTemp temp : instr.uses()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "def");
				for (MemTemp temp : instr.defs()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "in");
				for (MemTemp temp : instr.in()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "out");
				for (MemTemp temp : instr.out()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.endElement();
			}
			logger.endElement();
			logger.endElement();
		}
	}

}
