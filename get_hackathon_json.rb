require 'httparty'

hackathons = HTTParty.get("https://www.hackerleague.org/api/v1/hackathons.json?limit=100")
hackathons_with_hacks = hackathons.map do |hackathon|
  hacks = HTTParty.get("https://www.hackerleague.org/api/v1/hackathons/#{hackathon['id']}/hacks.json")
  hackathon["hacks"] = hacks
  hackathon
end
json = hackathons_with_hacks.to_json
json.gsub!(/\'/, "")
json.gsub!(/\"/, "\'")
json.gsub!(/\\\'/, "")
File.open('hackathons.txt', 'w') {|file| file.write(json)}