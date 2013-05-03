require 'httparty'

hackathons = HTTParty.get("https://www.hackerleague.org/api/v1/hackathons.json")
good_hackathons = hackathons.select {|hackathon| hackathon["total_hacks"] > 10}
good_hackathons_with_hacks = good_hackathons.map do |hackathon|
  hacks = HTTParty.get("https://www.hackerleague.org/api/v1/hackathons/#{hackathon['id']}/hacks.json")
  hackathon["hacks"] = hacks
  hackathon
end
json = good_hackathons_with_hacks.to_json
p good_hackathons_with_hacks.length
json.gsub!(/\'/, "")
json.gsub!(/\"/, "\'")
json.gsub!(/\\\'/, "")
File.open('hackathons.txt', 'w') {|file| file.write(json)}