<?php

require_once("SimpleRest.php");

class factorialRestHandler extends SimpleRest
{
    function factorial($num)
    {
        $factorial = 1;
        for ($x = $num; $x >= 1; $x--) {
            $factorial = $factorial * $x;
        }
        return $factorial;
    }

    function esPrimo($num)
    {
        if (is_numeric($num)) {
            if ($num == 2 || $num == 3 || $num == 5 || $num == 7) {
                return True;
            } else {
                if ($num % 2 != 0) {
                    for ($i = 3; $i <= sqrt($num); $i += 2) {
                        if ($num % $i == 0) {
                            return False;
                        }
                    }
                    return True;
                }
            }
        }
        return False;
    }
    
    function calcularFactorial($exten)
    {
        if ($exten < 0) {
            $rawData = "ERRORFACTORIAL";
            // $statusCode = 404;
            $statusCode = 200;
        } else {
            $rawData = $this->factorial($exten);
            $statusCode = 200;
        }
        
        $requestContentType = 'application/json';
        $this->setHttpHeaders($requestContentType, $statusCode);
        $response = json_encode($rawData);
        echo $response;
    }
    
    
    
    function verSiEsPrimo($exten)
    {
        if (is_numeric($exten) && $exten > 0) {
            if ($this->esPrimo($exten)) {
                $rawData = "ES PRIMO";
            } else {
                $rawData = "NO ES PRIMO";
            }
            $statusCode = 200;
        } else {
            $rawData = "ERRORPRIMO";
            // $statusCode = 404;  // esto estableceria error en la respuesta, que iria vacia, pero permite analizar el codigo
            $statusCode = 200;
        }

        $requestContentType = 'application/json';
        $this->setHttpHeaders($requestContentType, $statusCode);
        $response = json_encode($rawData);
        echo $response;
    }
}
