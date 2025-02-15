def swap_first_and_last_words_solution(s)
  words = s.split

  if words.length <= 1
    return s
  end

  words[0], words[-1] = words[-1], words[0]

  return words.join(' ')
end
