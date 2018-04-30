package com.zyy.Yggdrasil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.zyy.YingUtils.YingUUIDUtils;



public class YingYggdrasil {
	
	public static String Ying = "-----BEGIN PRIVATE KEY-----\nMIIJRQIBADANBgkqhkiG9w0BAQEFAASCCS8wggkrAgEAAoICAQDasLg9kDAOf5dn\nxKNvkOjvtw82V4oZfwnV0e5Jo1TQqpkEQiis3ES8MpoTJuTAziVACIug/pV7R8Gl\nz6JJHt+YF4vhSAiV4+UJaNRKaIXlh4hfet9vsv8Ko0GWj9Zlyojn49W0Yr0zX9kX\nkGnf1Hp4MlUtcj77ZT5WF8JAdIFlEXUMCdi5DIrp3nu82OOY40SQRFBcAvoKbfQ6\nO5rKdOqknTS7OswmABzAyhkOWagQpqnIgQkDleXoYvQrzijxHbsQaz00AIPRQYeK\n+qart3pnOxD7AyVksi05pg1coF3km+/1pRQM32Ek+3BKvldpZT3pdAe4drVZCxe8\n0FHO/QNKq4AUGljrrZA66p5DiEej1WiNXEH28+toOZLBp9sBkTRd+zPEyFg/NJb2\nIroJ4r3c1HZiBl5CnYk/4FP4/sCfNGKGwLkz6Tufyl6Z+DUrs8klDa8WwmYWZmnU\nJCE7L2cEbfsATW7imSsCWmxqGFqXVz6VkTtaH+m7VjKhuxVYQ/fBwpSm3mA3Ockr\nuUGB+JGcqgD49JzSP5bAVXndoI3EdqlhWBDfIhcMPIySFj7bMeg4qGcxlgpkCf/z\nXVpu5AJORlcTh/budJAr/XWad7S2ktjMtQpnheoFL/lBdaE7B90Z/waGqAV6Llp4\naUOMn2D5YkB7wLyexMeezGrMflVH5wIDAQABAoICAQCweMyj+Teea32tIfPU8rXV\nwv4Glwt82ZQBNF+6ko+9tvy1gAG8xB6/U4IblFYh8/gBPSQXrXjIu7XbbLsDkGNU\nIyQgjfVHp1hKiIpO5RuvhfgZPFkTlPjUB7vEmJulFpP1tFfeMZOF4oGXz1RwZ9DQ\nL6Wmf7utOBzecluxkZJdEYJHTabSZPmwEXX+hXLcDAFm/YlvRoOpaOMdHr4hQ8mZ\nPravo5nbzFlnhW5Eszq4/RSjFOVGPG8DYN6yfrFxKNGsRS1lSkkU0nFjSDK4/N1M\nclCYjghdwp5WF9ZOeaZq9R7lNeoBHT0SD8ikuAZnqWgVfClCxKBAk3/7QIvToT5G\nQ3hUqDIf+IPgNYW86zU+J/GroNp1YjeT3g+6bRlRZSNnyFimtbmNoPuKWR+G5EGw\nJoWkZLiDs5uaJXRtv/UIXUSMWsUdz63oh/S4VfO9SU64flgDeoqCW6GqIwgjFJ+L\nDn8eYGL6AxwlZc3fZJ4PkQJHrlAoYOxms3dax4jOY84ABMDzKhNF1NBRmtqDIMvk\nZptiwOp2c04e4MpnegIKWMH5h64G/kwd4YzcsceonqPSX4KK9mwt4YtL0knnAAbp\nevZqDAToQh5tioiy4uyPGEORT7noPzEtuueUyjanmMZALCUeuJoofQOjkIWFR3yB\nloVjQuP24HMwPoHnS5sw8QKCAQEA/Oqh8mlBYq93MxZZsIFp60tY1Cw5zs8lgX7U\nI6QbL1JHdpfCwesqcX7yWxSAZ2N1ty4wp9rXKag/TBab5lgKBlDYksuX7Zonpl3a\nVROnFjuDoGI1AZIOClrHknVor3QiLHBmUjn0j91QsAN+q7fYrVAfooz49zVVTeI3\n5o5OYX3WkMfNnXqB662PC3RmlizpKjiV1vf7XFwAZmAHT04YXJhIbw94KH8Uz0Vg\nCz8R1QlMIeFkqp2RwWMwfBbBa5PLPVbYov6nHiN+dsisAFaFqoG5t/xKVVtXR6Ww\ngvAeiknMfQbCRk2bC8kBaPOLgRqoSkKnzCkM/Fommqe7FAb8qQKCAQEA3VtD2O6s\nC+uyNgczwDWETiwKBd6weT3nLHM1qbir8XzzptX5lMPvmPWopVJnJdqheQYX9qbj\n6aWKVwzyMQ7yMn5u5nyom8Me6uuWqFMa3FFPydKmgB9uiAqMYlZq2iC6ChNvFx3R\nhPfHaQj08FRTHihBVJzGghgOQv4ijTE1az0Jx3q4y+o+kT4hBIu7x3jFDJDN+jp1\nZa7x4DblAcMV7hUr+XiHRnzLetaPonexhB3zHezma6n8p9K0Q1fMidCmS506acAP\n9vypOQ8GrmZMkq+Lcpw7ZmN8eBkraSB8omAFTpWt3TloeWyUsDuHefpQDYarp7+s\nlDhueI0yaWrqDwKCAQEArhIHGEKdQACoN78gR70qW0lmf9FYkCA+HhxegZFHLJCi\notrcrn7enXLT9x/rKD1qHYNq9XxHLmKf/gxpjGX3sFUgWhhKHrxEodpilL+L9l9L\nvq0Y8lXF2BaW2RR50phJlPBfXCSLUCDwkdQ18JX+SXql60i/adWJOquB8errw2nK\nrab2u3Y/ySeRFj8tBBsw92AusN/IXs7rb4qUPV7woXomEFpDRwwAFJAZBGSKL2fQ\nwFjS4iLE2KceDanTwejcrwEA5V4DQ1kGh1aiGzFxmeLjIeBAgPL25VcCX3xIaMFW\nPo7SU3TCS6AymjtVPqxMO6AYwQlX6K9bdoIO3j/Y0QKCAQEAjAVfNapESoYdhO+p\nXLx9pLVW4b+sAgWWZhe6qlGW8nsH5VEVP3Vx6l/6/hjSBq/11Ih2QB/xq6p7h/U4\nfq/lpOs1W7cfMOiMdi3wbymipBsLTa9ig0lesc96XfosPS7Wf+krLIRANMxH57ky\nt5OfwEPWj59cMo7k9purX4TxNZS2yuhmXRcf3OBgInABqHxMqVYv9UllWZ0Ag8Q1\nuJefF6ts1bHYYE4wqNNwqGTudZQ8ZYGlgBVXLRGr1a2EROknSE181cgYUTQiF2XZ\n5Bfx+BfzLdyF9o6X/aBuRWG3dT8oHD2ybW4TGDwHEjOVzyL34JZ/nu0wTdIkjz1B\n1k07IQKCAQEA7KBhTYjPtEcbPDcFv3s2GNXQ1pTQnkFNAYu/GMxSp7nZrCuMyad5\nSGtxTDyWM27n6eBCqujJta9QV7tqIXGgD4HAKhJnK4ieG7ynjCCZNH92L+zsiTEO\nLjs1hLOcMPkePdcFju/kD7R+zQUl04NYlmmhml25jkqmN9xvXZMqvAcL3ubWb/ox\nNgiKd+6U5gQS+DXNmLEhoeO8religwCmbjz0s8n0xkTYg80izo7mHMfFAV1ibTx4\nXtKI5PO3mvemVd2Tqr6Y8oAp51UFFnf6XutWCugnW2QuosLeHjmgXBIg3qDHfHuU\nmSEe8xVkY8iUGTeWGLO0fVvfVIVVA69X/g==\n-----END PRIVATE KEY-----\n";
	public static String YingPublic = "-----BEGIN PUBLIC KEY-----\nMIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA2rC4PZAwDn+XZ8Sjb5Do\n77cPNleKGX8J1dHuSaNU0KqZBEIorNxEvDKaEybkwM4lQAiLoP6Ve0fBpc+iSR7f\nmBeL4UgIlePlCWjUSmiF5YeIX3rfb7L/CqNBlo/WZcqI5+PVtGK9M1/ZF5Bp39R6\neDJVLXI++2U+VhfCQHSBZRF1DAnYuQyK6d57vNjjmONEkERQXAL6Cm30OjuaynTq\npJ00uzrMJgAcwMoZDlmoEKapyIEJA5Xl6GL0K84o8R27EGs9NACD0UGHivqmq7d6\nZzsQ+wMlZLItOaYNXKBd5Jvv9aUUDN9hJPtwSr5XaWU96XQHuHa1WQsXvNBRzv0D\nSquAFBpY662QOuqeQ4hHo9VojVxB9vPraDmSwafbAZE0XfszxMhYPzSW9iK6CeK9\n3NR2YgZeQp2JP+BT+P7AnzRihsC5M+k7n8pemfg1K7PJJQ2vFsJmFmZp1CQhOy9n\nBG37AE1u4pkrAlpsahhal1c+lZE7Wh/pu1YyobsVWEP3wcKUpt5gNznJK7lBgfiR\nnKoA+PSc0j+WwFV53aCNxHapYVgQ3yIXDDyMkhY+2zHoOKhnMZYKZAn/811abuQC\nTkZXE4f27nSQK/11mne0tpLYzLUKZ4XqBS/5QXWhOwfdGf8GhqgFei5aeGlDjJ9g\n+WJAe8C8nsTHnsxqzH5VR+cCAwEAAQ==\n-----END PUBLIC KEY-----";
	
	public static Map<String, YingUserInfo> YUsers = new HashMap<String, YingUserInfo>();
	
	public void Ying() {
		
	}
	
	public boolean Authenticate(JSONObject YUserInfo) {
		
		if(!YUserInfo.get("username").toString().equals("1340761826@qq.com")) {
			return false;
		}
		
		
		//output.write(YResponseBuilder(200, "{\"accessToken\": \"869a97cb-2bc8-41be-84bf-d668c299a718\",\"clientToken\": \"c8d53327-5e7b-491a-a3d6-6b612d4cd9ab\",\"availableProfiles\": [{\"id\": \"d3af753b7cda4666adc2ff9bba85e0eb\",\"name\": \"621sama\"}],\"selectedProfile\": {\"id\": \"d3af753b7cda4666adc2ff9bba85e0eb\",\"name\": \"621sama\"},\"user\": {\"id\": \"d3af753b7cda4666adc2ff9bba85e0eb\",\"properties\": []}}").getBytes());
		YingUserInfo YingUser = new YingUserInfo();
		YingUser.setUsername(YUserInfo.get("username").toString());
		YingUser.setPassword(YUserInfo.get("password").toString());
		if(YUserInfo.get("clientToken") == null) {
			YingUser.setClientToken(YingUUIDUtils.toUUID(YingUUIDUtils.randomUnsignedUUID()).toString());
		} else {
			YingUser.setClientToken(YUserInfo.get("clientToken").toString());
		}
		YingUser.setRequestUser((boolean)YUserInfo.get("requestUser"));
		
		JSONObject YAgent = (JSONObject) YUserInfo.get("agent");
		Map<String, String> Y = new HashMap<String, String>();
		Y.put("name", YAgent.get("name").toString());
		Y.put("version", YAgent.get("version").toString());
		
		YingUser.setAgent(YAgent);
		
		YUsers.put(YUserInfo.get("clientToken").toString(), YingUser);
		
		return true;
	}
	
}
