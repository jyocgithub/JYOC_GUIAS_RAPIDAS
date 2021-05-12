<?php

//Fichero neecsarios para el desarrollo de la clase
require_once("SimpleRest.php");
require_once("../cliente/include/DB.php");
require_once("../cliente/include/billete.php");

/**
 * Clase Manejadora para el acceso a datos, devolución de códigos de errores y envío de información en formato json.
 * 
 */
class billeteRestHandler extends SimpleRest
{

	function getDescuento($num_billete)
	{
		$billete = DB::getBillete($num_billete);
		$pvp = $billete->getPvp();
		$descuento = $pvp * 0.10;
		$rawData = $descuento;

		if (empty($rawData)) {
			$statusCode = 404;
			$rawData = array('error' => 'No se ha encontrado ningun billete con dicho código.');
		} else {
			$statusCode = 200;
		}

		$requestContentType = 'application/json';
		$this->setHttpHeaders($requestContentType, $statusCode);

		// if (strpos($requestContentType, 'application/json') !== false) {
			$response = $this->encodeJson($rawData);
			echo $response;
		// }
	}




	/**
	 * Método que recibe un parámetro de tipo array y devuelve un string con la representación JSON del valor dado.
	 * @param string[] $responseData
	 * @return string
	 */
	public function encodeJson($responseData)
	{
		$jsonResponse = json_encode($responseData);
		return $jsonResponse;
	}
}
