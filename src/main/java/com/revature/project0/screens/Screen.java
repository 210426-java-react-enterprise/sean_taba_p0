package com.revature.project0.screens;

import com.revature.project0.models.CheckingAccount;
import com.revature.project0.models.SavingsAccount;
import com.revature.project0.models.TrustAccount;
import com.revature.project0.utilities.CurrentAccount;

public abstract class Screen {

        private final String identifier;

        protected Screen(String identifier)
        {
            this.identifier = identifier;
        }

        public abstract void render();

        public String getIdentifier()
        {
            return identifier;
        }
        protected String getAccountIdentifier()
        {
            String identifier = "";
            if (CurrentAccount.getInstance().getAccount() instanceof CheckingAccount)
            {
                identifier = "c";
            } else if (CurrentAccount.getInstance().getAccount() instanceof SavingsAccount)
            {
                identifier = "s";
            } else if (CurrentAccount.getInstance().getAccount() instanceof TrustAccount)
            {
                identifier = "t";
            }
            return identifier;
        }

}
