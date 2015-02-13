#!/usr/bin/perl
use strict;
use Net::XMPP;
my ($recip, $msg) = @ARGV;
if(! $recip || ! $msg) {
    print "Syntax: $0 <recipient> <message>\n";
    print "Example : $0 dewgongmafiaadmin\@chat.facebook.com hello\n";
    exit;
}
my $con = new Net::XMPP::Client();
my $status = $con->Connect(
    hostname => 'chat.facebook.com',
    connectiontype => 'http',#'tcpip',
    port => 5222,
    tls => 1);
die('ERROR: XMPP connection failed') if ! defined($status);
my @result = $con->AuthSend(
    hostname => 'chat.facebook.com',
    username => 'dewgongmafiaadmin',
    password => 'harrisisgay');
    
die('ERROR: XMPP authentication failed') if $result[0] ne 'ok';
#print 'result: ' + $result[0];
die('ERROR: XMPP message failed')
    if ($con->MessageSend(to => $recip, body => $msg) != 0);
print "Success!\n";
print $recip;
print "\n";
print $msg;
print "\n";

#$con->PresenceSend();
#print "Online!";
if(defined($con->Process(3))) {}
print "Done!\n";
