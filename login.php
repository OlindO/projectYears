
class RocketChatAPI{
    

$APIBASEURI = "http://rocket.chat/api/info";

{
   "status": "success",
   "versions": {
      "api": "0.1",
      "rocketchat": "0.5"
   }
}
function RocketChatLogin($CHATHOSTNAME, $user, $mdp)
{
    
    curl http://rocket.chat/api/info;
  
    $url = $CHATHOSTNAME.'/login';
    $curl = curl_init($url);
    $curl_post_data = array(
        'password' => $mdp,
        'user' => $user
    );
    // on va récuprer la chaine encodé JSON et la convertir en une variable PHP
    $curl_response = json_decode(curl_exec($curl));
    // fermeture de la session 
    curl_close($curl);
    if($curl_response->status === "success"){
        echo "Connection Réussis " !';
        return $curl_response;
    } else {
        echo "Connection échoué !";
    }
}

// Cette fonction va nous permettre de retourner notre élément du tableau qui est le channel 
// Il nous suffira de faire un $this pour appeler notre fonction 

public function getAPIChannel()
{
    $APIBASEURI = "http://rocket.chat/";
    $rocketChannelSection = array("channel" => $APIBASEURI."channel/")
    return $rocketChannelSection;
}

}

