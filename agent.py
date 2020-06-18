def agent_template()
    percepts = getPercepts()
    
    if not percepts:
        setDebugString("...")
    else:
        for percept in percepts:
            if isEnemy(percept):
                if percept.getType().equals(WarAgentType.WarLight):
                    enemy_war_light_func()
                elif percept.getType().equals(WarAgentType.Heavy):
                    enemy_war_heavy_func()
                elif percept.getType().equals(WarAgentType.Explorer):
                    enemy_war_explorer_func()
                elif percept.getType().equals(WarAgentType.RocketLauncher):
                    enemy_war_rocket_func()
                elif percept.getType().equals(WarAgentType.WarBase):
                    enemy_base_func()
                else:
                    setDebugString("...")
            else:
                if percept.getType().equals(WarAgentType.WarLight):
                    war_light_func()
                elif percept.getType().equals(WarAgentType.Heavy):
                    war_heavy_func()
                elif percept.getType().equals(WarAgentType.Explorer):
                    war_explorer_func()
                elif percept.getType().equals(WarAgentType.RocketLauncher):
                    war_rocket_func()
                elif percept.getType().equals(WarAgentType.WarBase):
                    war_base_func()
                else:
                    setDebugString("...")
                    
    if isBlocked():
        RandomHeading()
        
    return move()
