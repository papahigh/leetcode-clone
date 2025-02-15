<?php

class Solution
{
    function swapFirstAndLastWords($s)
    {
        $words = explode(' ', $s);

        if (count($words) <= 1) {
            return $s;
        }

        $temp = $words[0];
        $words[0] = $words[count($words) - 1];
        $words[count($words) - 1] = $temp;

        return implode(' ', $words);
    }
}
