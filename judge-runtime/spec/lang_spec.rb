require 'rspec'

RSpec.describe 'Docker Container Check' do
  IMAGE_NAME = 'papahigh/judge-runtime:latest'
  LANG_DIRS = %w[
    c
    cpp
    cs
    dart
    elixir
    erlang
    go
    java
    js
    kotlin
    php
    python3
    ruby
    rust
    swift
    ts
  ]

  LANG_DIRS.each do |dir|
    it "should have #{dir} language support" do
      system("docker run --rm -v #{__dir__}/lang.#{dir}:/tmp/app -w /tmp/app #{IMAGE_NAME} sh check.sh")
      expect($?.exitstatus).to eq(0), "expected exit code 0 for #{dir}, got #{$?.exitstatus}"
    end
  end

end
