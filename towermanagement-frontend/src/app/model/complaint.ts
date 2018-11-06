import { Tower } from "./tower";
import { CSA } from "./csa";

export class Complaint {
    public complaintId:number;
	public type:string;
	public description:string;
	public dateOfIssue:string;
	public dateOfApproval:string;
	public viewStatus:boolean;
	public actionStatus:string;
	public tower:Tower;
	public csa:CSA;
	
}
