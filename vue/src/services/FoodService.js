import axios from "axios";

export default {
    getFoodByName(searchName)
    {
       const url = `/foodByName/${searchName}`;
       return axios.get(url)
       .then(response =>{
           return response.data;
       })
       .catch(error =>{
           console.error(error);
           throw error;
       });
    },
}