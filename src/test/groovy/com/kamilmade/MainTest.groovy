package com.kamilmade

import com.kamilmade.config.ConfigEnums
import com.kamilmade.enums.Events
import com.kamilmade.enums.States
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.statemachine.StateMachine
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = ConfigEnums)
class MainTest extends Specification {
    @Autowired
    StateMachine<States,Events> stateMachine;

    def "free -> selected -> free"(){
        given:
        stateMachine.start();
        stateMachine.getState().getId() == States.FREE

        when:
        stateMachine.sendEvent(Events.SELECT)

        then:
        stateMachine.getState().getId() == States.SELECTED

        when:
        stateMachine.sendEvent(Events.CANCEL);

        then:
        stateMachine.getState().getId() == States.FREE;
        stateMachine.isComplete() == false
    }

    def "free -> selected -> reserved -> free"(){
        given:
        stateMachine.start();
        stateMachine.getState().getId() == States.FREE

        when:
        stateMachine.sendEvent(Events.SELECT)

        then:
        stateMachine.getState().getId() == States.SELECTED

        when:
        stateMachine.sendEvent(Events.RESERVE)

        then:
        stateMachine.getState().getId() == States.RESERVED

        when:
        stateMachine.sendEvent(Events.CANCEL)

        then:
        stateMachine.getState().getId() == States.FREE
        stateMachine.isComplete() == false

    }

    def "free -> selected -> reserved -> bought"(){
        given:
        stateMachine.start();
        stateMachine.getState().getId() == States.FREE

        when:
        stateMachine.sendEvent(Events.SELECT)

        then:
        stateMachine.getState().getId() == States.SELECTED

        when:
        stateMachine.sendEvent(Events.RESERVE)

        then:
        stateMachine.getState().getId() == States.RESERVED

        when:
        stateMachine.sendEvent(Events.PAY)

        then:
        stateMachine.isComplete() == true
    }


}
