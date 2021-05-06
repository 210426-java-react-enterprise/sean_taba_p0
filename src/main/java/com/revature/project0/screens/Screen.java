package com.revature.project0.screens;

public abstract class Screen {

        private final String identifier;

        protected Screen(String identifier)
        {
            this.identifier = identifier;
        }

        public abstract void render();

        protected void clearScreen()
        {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }

        public String getIdentifier()
        {
            return identifier;
        }

}
