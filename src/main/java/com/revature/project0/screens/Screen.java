package com.revature.project0.screens;

import com.revature.project0.models.CheckingAccount;
import com.revature.project0.models.SavingsAccount;
import com.revature.project0.models.TrustAccount;
import com.revature.project0.utilities.CurrentAccount;
import exceptions.IllegalInputException;

public abstract class Screen {

        private final String identifier;


        protected Screen(String identifier)
        {
            this.identifier = identifier;
        }

        public abstract void render() throws IllegalInputException;

        public String getIdentifier()
        {
            return identifier;
        }


}
