import axios from 'axios';

export default 
    {
        getMyGoals() 
            {
                return axios.get('/myGoals');
            },
        async createGoal(newGoal) 
            {
                        const response = await axios.post(`/goals/create`, newGoal);
                        return response;
            }
    };