package com.enernoc.open.oadr2.vtn


/**
 * Model class for VenStatus that persists unto the database
 * Each VenStatus belongs to an Event and VEN
 * 
 * @author Yang Xiang
 *
 */

class VenStatus {

    static belongsTo = [event: Event, ven: Ven]
    StatusCode optStatus
    Date time
    String requestID
    static constraints = {
        requestID nullable: true
        time nullable: false
    }

    /**
     * Return the string representation of Venstatus
     * 
     * @return String
     */
    public String toString(){
        "VEN Status \n  VEN ID: $ven.venID\n  Event ID: $event.eventID\n  Program: $event.program.name" +
                "\n  Status: $optStatus\n  Time: $time"
    }

    public String getStatusText() {
        switch(this.optStatus) {
            case(StatusCode.PENDING_DISTRIBUTE) :
                return "Pending Distribute"
            case(StatusCode.DISTRIBUTE_SENT) :
                if (this.event.responseRequired) { 
                    return "Awaiting Response"
                } else {
                    return "Payload Sent"
                }
            case(StatusCode.OPT_IN) :
                return "optIn"
            case(StatusCode.OPT_OUT) :
                return "optOut"
            default: 
                return null
        }
    }


    /**
     * Formats the date as readable format
     *
     * @return a String of a readable DateTime
     */
    public String displayTime(){
        return time.format("dd/MM/yyyy HH:mm")
    }

}